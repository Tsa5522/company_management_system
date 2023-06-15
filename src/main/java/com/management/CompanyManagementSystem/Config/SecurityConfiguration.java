package com.management.CompanyManagementSystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;


@Configuration
public class SecurityConfiguration {

    private final CustomAuthenticationProvider customAuthProvider;

    public SecurityConfiguration(CustomAuthenticationProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(customAuthProvider)
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/", "/login").permitAll()  // permit register url
    //                .requestMatchers("/users/all").hasRole("ADMIN") //only allow ADMIN access to /users
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index")
                        .failureUrl("/login?error")
                );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Collections.singletonList(customAuthProvider));
    }
}



