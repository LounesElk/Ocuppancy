package com.itso.occupancy.occupancy.config.security;

import com.itso.occupancy.occupancy.helper.tool.enumeration.RoleEnum;
import com.itso.occupancy.occupancy.service.user.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Data
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@AllArgsConstructor
public class WebSecurityConfig  {

    // Parameters
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthTokenFilter 	    jwtAuthTokenFilter;
    private final AuthExceptionEntryPoint   authExceptionEntryPoint;




    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManagerBean() {
        return new AuthenticationManagerImpl();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }

        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(authExceptionEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/jwt").permitAll()
                .and()
                .authorizeRequests()
                //.antMatchers("/users").permitAll()
                //.antMatchers("/job").permitAll()


                // all other requests need to be authenticated
                .anyRequest()
                .authenticated().and()
                .logout()
                .permitAll();


        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
