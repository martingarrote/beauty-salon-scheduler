package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.user.dto.LoginDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.ProfileDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.SignupDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupDTO dto) {
        service.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(dto));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> profile(Authentication authentication) {
        var email = getUserEmail(authentication);

        if (email == null) {
            throw new RuntimeException("Error to identify user");
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.profile(getUserEmail(authentication)));
    }

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
