package com.martingarrote.beauty_salon_scheduler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private Long getUserId(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return user.getId();
        }
        return null;
    }

    private String getUserEmail(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return user.getEmail();
        }
        return null;
    }

}
