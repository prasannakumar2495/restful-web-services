package com.prasanna.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /*
         * 1. All requests should be authenticated.
         * 2. If a request is snot authenticated, a web page is shown.
         * 3. CSRF -> POST,PUT
         */

        httpSecurity.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated()
        );

        httpSecurity.httpBasic(withDefaults());

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
