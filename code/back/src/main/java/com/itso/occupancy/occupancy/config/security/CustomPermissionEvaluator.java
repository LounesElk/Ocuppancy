package com.itso.occupancy.occupancy.config.security;


import com.itso.occupancy.occupancy.config.errorhandler.customexception.UserNotAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Slf4j
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean hasPermission = userDetails.getAuthorities().stream()
                .map(Object::toString)
                .anyMatch(authority -> authority.equals(permission));

        if (!hasPermission)
            throw new UserNotAuthorizeException(String.format("The user [username = %s] is not authorized to access this resource",userDetails.getUsername()));

        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;

    }
}
