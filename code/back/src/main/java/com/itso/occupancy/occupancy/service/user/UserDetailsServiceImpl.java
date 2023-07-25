package com.itso.occupancy.occupancy.service.user;

import com.itso.occupancy.occupancy.model.User;
import com.itso.occupancy.occupancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);

        User user = userRepository.findByUsernameAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndUsername(username,username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities()
        );
    }
}
