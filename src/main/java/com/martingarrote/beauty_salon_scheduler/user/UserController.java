package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.mapper.common.PageDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.*;
import com.martingarrote.beauty_salon_scheduler.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.martingarrote.beauty_salon_scheduler.utils.AuthenticationUtils.getUserEmail;
import static com.martingarrote.beauty_salon_scheduler.utils.AuthenticationUtils.getUserId;

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

        return ResponseEntity.status(HttpStatus.OK).body(service.profile(email));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> findEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findEmployees());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FullUserDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullUserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("search")
    public PageDTO<FullUserDTO> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String authority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.search(name, email, authority, page, size);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FullUserDTO> patchUpdate(@PathVariable Long id, @RequestBody UserPatchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.patchUpdate(id, dto));
    }

    @PatchMapping("/profile")
    public ResponseEntity<ProfileDTO> updateProfile(Authentication authentication, @RequestBody UserPatchDTO dto) {
        ProfileDTO updatedUser = service.updateProfile(getUserId(authentication), dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

}
