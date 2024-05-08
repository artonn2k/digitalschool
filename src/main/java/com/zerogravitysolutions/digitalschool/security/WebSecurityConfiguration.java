package com.zerogravitysolutions.digitalschool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfiguration {

    private JwtAuthConverter jwtAuthConverter;

    public WebSecurityConfiguration(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(customizer ->{
           customizer
                   .requestMatchers("/trainings/**").hasRole("ADMINISTRATOR")
                   .anyRequest().authenticated();
        });

        http.oauth2ResourceServer(customizer ->{

            customizer.jwt(jwtCustomizer ->{
              jwtCustomizer.jwtAuthenticationConverter((jwtAuthConverter));
           });
        });

        http.sessionManagement(customizer -> {
           customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }
}
