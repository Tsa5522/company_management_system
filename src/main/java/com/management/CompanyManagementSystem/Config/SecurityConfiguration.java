package com.management.CompanyManagementSystem.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Collections;


@Configuration
public class SecurityConfiguration {

    private final CustomAuthenticationProvider customAuthProvider;


    public SecurityConfiguration(CustomAuthenticationProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(customAuthProvider)
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/css/**", "/js/**").permitAll()
                    .requestMatchers("/", "/login","/register").permitAll()  // permit register url
    //                .requestMatchers("/users/all").hasRole("ADMIN") //only allow ADMIN access to /users
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index")
                                .failureHandler(new SimpleUrlAuthenticationFailureHandler() {

                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                                        AuthenticationException exception) throws IOException, ServletException {
                                        String email = request.getParameter("email");
                                        String error = exception.getMessage();
                                        System.out.println("A failed login attempt with email: "
                                                + email + ". Reason: " + error);

                                        super.setDefaultFailureUrl("/login?error");
                                        super.onAuthenticationFailure(request, response, exception);
                                    }
                                })
//                        .failureUrl("/login?error")
                );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(){
        return new ProviderManager(Collections.singletonList(customAuthProvider));
    }

}



