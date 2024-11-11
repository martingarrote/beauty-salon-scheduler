package com.martingarrote.beauty_salon_scheduler.configurations;

import com.martingarrote.beauty_salon_scheduler.security.SecurityFilter;
import com.martingarrote.beauty_salon_scheduler.security.UserDetailsServiceImpl;
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

                            // Beauty Items
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/beauty-items").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/beauty-items").permitAll(); // or .authenticated()
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/beauty-items/{id}").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/beauty-items/search").permitAll(); // or .authenticated()
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/beauty-items/{id}").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.DELETE, "/api/beauty-items/{id}").hasAuthority("ADMIN");

                            // Users
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/profile").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/users/profile").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/employees").permitAll();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/all").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/users/search").hasAuthority("ADMIN");
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/users/{id}").hasAuthority("ADMIN");

                            // Appointments
                            authorizeHttp.requestMatchers(HttpMethod.POST, "/api/appointments").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/appointments/history").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.GET, "/api/appointments").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/appointments/cancel/{id}").authenticated();
                            authorizeHttp.requestMatchers(HttpMethod.PATCH, "/api/appointments/reschedule/{id}").authenticated();

                            // Other requests
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