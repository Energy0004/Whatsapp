package com.example.whatsapp.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//                        ).addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
//                .csrf().disable()
//                .cors().configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration cfg = new CorsConfiguration();
//
//                        cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
//                        cfg.setAllowedMethods(Collections.singletonList("*"));
//                        cfg.setAllowCredentials(true);
//                        cfg.setAllowedHeaders(Collections.singletonList("*"));
//                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                        cfg.setMaxAge(3600L);
//
//                        return cfg;
//                    }
//                })
//                .and().formLogin().and().httpBasic();
//
//        return http.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").permitAll().
                        requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cfg = new CorsConfiguration();

                        cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                        cfg.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));


                        cfg.setAllowedMethods(Collections.singletonList("*"));
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        cfg.setMaxAge(3600L);

                        return cfg;
                    }
                })).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}