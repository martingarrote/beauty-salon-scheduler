package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.authority.Authority;
import com.martingarrote.beauty_salon_scheduler.authority.AuthorityRepository;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailAlreadyInUseException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.WrongPasswordException;
import com.martingarrote.beauty_salon_scheduler.mapper.UserMapper;
import com.martingarrote.beauty_salon_scheduler.security.TokenService;
import com.martingarrote.beauty_salon_scheduler.user.dto.*;
import com.martingarrote.beauty_salon_scheduler.user.enums.FaceShape;
import com.martingarrote.beauty_salon_scheduler.user.enums.HairCurl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper = UserMapper.INSTANCE;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private static final String AUTHORITY_USER = "ROLE_USER";
    private static final String AUTHORITY_EMPLOYEE = "ROLE_EMPLOYEE";
    private static final String AUTHORITY_ADMIN = "ROLE_ADMIN";

    @Transactional
    public void signup(SignupDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyInUseException();
        }

        Set<Authority> userAuthorities = Set.of(getAuthority(AUTHORITY_USER));

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .authorities(userAuthorities)
                .build();

        repository.save(user);
    }

    public TokenDTO login(LoginDTO dto) {
        User user = repository.findByEmail(dto.email())
                .orElseThrow(EmailNotFoundException::new);

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new WrongPasswordException();
        }
        
        return new TokenDTO(tokenService.generateToken(user));
    }

    public UserDTO patchUpdate(Long id, UserPatchDTO dto) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        if (dto.name() != null) {
            user.setName(dto.name());
        }
        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
        if (dto.faceShape() != null) {
            FaceShape value = FaceShape.valueOf(dto.faceShape().toUpperCase());

            user.setFaceShape(value);
        }
        if (dto.hairCurl() != null) {
            HairCurl value = HairCurl.valueOf(dto.hairCurl().toUpperCase());

            user.setHairCurl(value);
        }
        if (dto.about() != null) {
            user.setAbout(dto.about());
        }
        if (dto.instagram() != null) {
            user.setInstagram(dto.instagram());
        }

        return mapper.toDTO(repository.save(user));
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<EmployeeDTO> findEmployees() {
        var users = repository.findByAuthorities_Authority(AUTHORITY_EMPLOYEE);

        return users.stream().map(mapper::toEmployeeDTO).toList();
    }

    private Authority getAuthority(String authority) {
        return authorityRepository.findById(authority).orElseThrow();
    }

}
