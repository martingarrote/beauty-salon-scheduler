package com.martingarrote.beauty_salon_scheduler.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttp -> {

                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/appointments").hasRole("USER");

                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/profile").permitAll(); // SHOULD AUTHENTICATED
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/all").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/employees").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/beauty-items").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/beauty-items/search").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/appointments/history").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/appointments").authenticated();

                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/users/profile").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/users").hasRole("ADMIN");

                            // temporary
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/search").permitAll();

                            authorizeHttp.anyRequest().hasRole("ADMIN");
                        }
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}