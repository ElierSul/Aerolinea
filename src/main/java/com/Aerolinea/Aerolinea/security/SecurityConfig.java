package com.Aerolinea.Aerolinea.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/vuelos/**", "/usuarios/**", "/tipos/**", "/mediosPago/**",
                            "/escalas/**", "/boletos/**", "/aerolineas/**").permitAll();
                    auth.requestMatchers("/javainuse-openapi/**", "/swagger-ui/**").authenticated();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults());

        return http.build();
    }
}
