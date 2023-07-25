package com.itso.occupancy.occupancy.config.security;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.BadCredentialsException;
import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.config.errorhandler.customexception.UserDisabledException;
import com.itso.occupancy.occupancy.config.errorhandler.customexception.UserLockedException;
import com.itso.occupancy.occupancy.helper.enumeration.SecurityJWTEnum;
import com.itso.occupancy.occupancy.model.User;
import com.itso.occupancy.occupancy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;


@Slf4j
public class AuthenticationManagerImpl implements AuthenticationManager {


    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getPrincipal() + "";
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }

    public Authentication authenticate(Authentication authentication,
                                       PasswordEncoder passwordEncoder,
                                       UserRepository userRepository) {

        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsernameAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndUsername(username, username)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find User [username = %s]", username)));

        // check if tries should be reset
        canResetFailedAttempt(user);
        // check if user account is locked
        if(!user.isAccountNonLocked()) {
            log.info(String.format("%s is locked",  username));
            throw new UserLockedException(String.format("Account [username = %s] is locked because of 5 failed attempt, please try again later", username));
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            addFailedAttempt(user);
            canLockUserAccount(user);
            userRepository.save(user);
            if(!user.isAccountNonLocked()) {
                log.info(String.format("%s is locked",  username));
                throw new UserLockedException(String.format("Account [username = %s] is locked because of 5 failed attempt, please try again later", username));
            }
            log.info(String.format("Login attempt failed: wrong password for [username = %s]", username));
            throw new BadCredentialsException(String.format("Wrong password for User [username = %s]", username));
        }
        if (!user.isEnabled() || user.isDeleted())
            throw new UserDisabledException(String.format("User [username = %s] is %s", username, (user.isDeleted() ? "deleted" : "disabled")));

        // if here, then password is ok, reset locked information
        unlockUserAccount(user);
        setLastConnectionDate(user);
        userRepository.save(user);
        return authenticate(authentication);
    }




    /**
     * Check if we can reset User failed attempt
     * @param dbUser user to check
     */
    private void canResetFailedAttempt(User dbUser) {
        if(dbUser.getLastFailedAttempt() != null && Date.from(dbUser.getLastFailedAttempt()).before(new Date(System.currentTimeMillis() - (SecurityJWTEnum.ACCOUNT_LOCK_TIMER.get()))) ||
                (dbUser.getLastFailedAttempt() != null && dbUser.getLastConnection() != null && Date.from(dbUser.getLastFailedAttempt()).before(Date.from(dbUser.getLastConnection()))))
            this.unlockUserAccount(dbUser);
    }


    /**
     * Check if user should be locked
     * @param dbUser user to check
     */
    private void canLockUserAccount(User dbUser) {
        if(dbUser.getLastFailedAttempt() != null && dbUser.getNumberFailedAttempt() >= SecurityJWTEnum.JWT_AUTH_MAX_ATTEMPT.get())
            this.lockUserAccount(dbUser);
    }


    /**
     * Check if user can be unlocked
     * @param dbUser user to check
     */
    private void unlockUserAccount(User dbUser) {
        dbUser.setLastFailedAttempt(null);
        dbUser.setNumberFailedAttempt(0);
        dbUser.setAccountNonLocked(true);
    }


    /**
     * Add a fail attept to user
     * @param dbUser user to check
     */
    private void addFailedAttempt(User dbUser) {
        dbUser.setNumberFailedAttempt((dbUser.getNumberFailedAttempt() + 1));
        dbUser.setLastFailedAttempt(Instant.now());
    }


    /**
     * Lock user's account
     * @param dbUser user to check
     */
    private void lockUserAccount(User dbUser) {
        dbUser.setAccountNonLocked(false);
    }


    /**
     * Set last connection of user
     * @param dbUser user to check
     */
    private void setLastConnectionDate(User dbUser) {
        dbUser.setLastConnection(Instant.now());
    }

}
