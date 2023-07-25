package com.itso.occupancy.occupancy.config.security;

import com.itso.occupancy.occupancy.model.User;
import com.itso.occupancy.occupancy.repository.UserRepository;
import com.itso.occupancy.occupancy.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final JwtTokenUtil              jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Parameters
        String username = null;
        String jwtToken = null;

        // Get Authorization from Header
        final String requestTokenHeader = request.getHeader("Authorization");

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            // Get token
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (IllegalArgumentException e) {
                log.debug("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e) {
                log.debug("JWT Token has expired");
            }
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            final String userNameFinal = username;

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            User user = userRepository.findByUsernameAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndUsername(username, username)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", userNameFinal)));

            // if token is valid configure Spring Security to manually set
            // authentication
            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails) && user.isEnabled()) && !user.isDeleted()) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
