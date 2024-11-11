package com.martingarrote.beauty_salon_scheduler.security;

import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailNotFoundException;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository repository;
    private final TokenService tokenService;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        String email = tokenService.validateToken(token);

        if (email != null) {
            User user = repository.findByEmail(email).orElseThrow(EmailNotFoundException::new);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.mapAuthorityToSimpleGrantedAuthority());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null) {
            return null;
        }

        return authHeader.replace(BEARER, "");
    }
}
