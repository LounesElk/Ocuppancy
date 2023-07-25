package com.itso.occupancy.occupancy.controller.login;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.model.login.JwtAuthInputDto;
import com.itso.occupancy.occupancy.dto.model.login.JwtAuthPublicDto;
import com.itso.occupancy.occupancy.service.login.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth")
@AllArgsConstructor
public class JwtAuthController {

    private final LoginService loginService;

    @PostMapping("/jwt")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtAuthInputDto authenticationRequest, @RequestParam(required = false) boolean shortToken) {
        // Generate token
        final String token = loginService.Login(authenticationRequest, shortToken);
        return ResponseEntity.ok(new JwtAuthPublicDto(token));
    }

    @GetMapping("/signup")
    public String registerUser() {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Object userConnected = auth.getPrincipal();
//        if (userConnected instanceof UserDetails)
//            return ((UserDetails) userConnected).getUsername();

        throw new ElementNotFoundException(String.format("Unable to find User [username = %s]", "Michel"));

//        String encodedPw = passwordEncoder.encode("pw");
        // Return Token
//        return encodedPw;
    }
}
