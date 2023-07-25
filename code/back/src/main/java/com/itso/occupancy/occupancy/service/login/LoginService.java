package com.itso.occupancy.occupancy.service.login;


import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.config.security.AuthenticationManagerImpl;
import com.itso.occupancy.occupancy.config.security.JwtTokenUtil;
import com.itso.occupancy.occupancy.dto.model.login.JwtAuthInputDto;
import com.itso.occupancy.occupancy.model.User;
import com.itso.occupancy.occupancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class LoginService implements ILoginService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public String Login(JwtAuthInputDto authenticationRequest, boolean shortToken) {

        // Check information
        new AuthenticationManagerImpl().authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()),
                passwordEncoder,
                userRepository);

        // Get User in Database
        User user = userRepository.findByUsernameAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndUsername(authenticationRequest.getUsername(), authenticationRequest.getUsername())
                .orElseThrow(()->new ElementNotFoundException(String.format("Unable to find User [username = %s]",authenticationRequest.getUsername())));

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities()
        );

        // Generate token
        return jwtTokenUtil.generateToken(userDetails, shortToken,user.getRole().getId(),user.getFirstName(),user.getLastName(),user.getId());
    }



}
