package com.itso.occupancy.occupancy.config.audit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    private String currentUsername = "Anonymous";

    @Override
    public Optional<String> getCurrentAuditor() {

        // Get authentication (Security)
        Authentication currentContextAuth = SecurityContextHolder.getContext().getAuthentication();
        // Check authentication (Security)
        if (currentContextAuth != null ) {
            // Get principal == CurrentUser connected using Security lib
            try {
                UserDetails userDetails = (UserDetails)currentContextAuth.getPrincipal();
                currentUsername = userDetails.getUsername();
            }catch (Exception e){
                log.debug("Cannot cast to userDetails {} : ",e.getMessage());
            }
        }
        return Optional.ofNullable(currentUsername);
    }
}
