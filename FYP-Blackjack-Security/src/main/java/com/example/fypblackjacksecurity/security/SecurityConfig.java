package com.example.fypblackjacksecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests((requests) -> {
                    requests.requestMatchers("/").permitAll();
                    requests.requestMatchers("/home").permitAll();
                    requests.requestMatchers("/register").permitAll();
                    requests.requestMatchers("/index").permitAll();
                    requests.requestMatchers("/static/**").permitAll();
                    requests.anyRequest().authenticated();
                }
            )
            .formLogin((form) ->form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/account")
                    .successForwardUrl("/account")
                    .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return httpSecurity.build();
    }
}

