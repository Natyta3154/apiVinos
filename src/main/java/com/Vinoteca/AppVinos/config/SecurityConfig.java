package com.Vinoteca.AppVinos.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desactiva CSRF para APIs REST
                .cors(Customizer.withDefaults()) // usa CorsConfig
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // permite todos los endpoints
                );

        return http.build();
    }
}
