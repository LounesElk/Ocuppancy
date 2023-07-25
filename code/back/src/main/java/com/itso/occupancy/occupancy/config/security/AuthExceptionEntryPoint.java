package com.itso.occupancy.occupancy.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itso.occupancy.occupancy.helper.tool.LogHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@AllArgsConstructor
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Log Trace constraint exception
        log.trace(LogHelper.getStackTraceException(authException));

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        String error = "Unauthorized";
        mapper.writeValue(response.getOutputStream(), error);
    }

}
