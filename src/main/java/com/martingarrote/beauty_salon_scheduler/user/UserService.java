package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.appointment.AppointmentService;
import com.martingarrote.beauty_salon_scheduler.authority.Authority;
import com.martingarrote.beauty_salon_scheduler.authority.AuthorityRepository;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailAlreadyInUseException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.UserNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.WrongPasswordException;
import com.martingarrote.beauty_salon_scheduler.mapper.UserMapper;
import com.martingarrote.beauty_salon_scheduler.mapper.common.PageDTO;
import com.martingarrote.beauty_salon_scheduler.security.TokenService;
import com.martingarrote.beauty_salon_scheduler.user.dto.*;
import com.martingarrote.beauty_salon_scheduler.user.enums.FaceShape;
import com.martingarrote.beauty_salon_scheduler.user.enums.HairCurl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final AppointmentService appointmentService;

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

    public ProfileDTO profile(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        int loyaltyPoints = appointmentService.getLoyaltyPoints(user.getId());
        String favEmployeeName = getFavoriteEmployeeName(user.getId());

        String faceShape = user.getFaceShape() != null ? user.getFaceShape().name() : null;
        String hairCurl = user.getHairCurl() != null ? user.getHairCurl().name() : null;

        return new ProfileDTO(
                user.getName(),
                user.getEmail(),
                faceShape,
                hairCurl,
                favEmployeeName,
                loyaltyPoints,
                user.getAbout(),
                user.getInstagram()
        );
    }

    private String getFavoriteEmployeeName(Long userId) {
        return appointmentService.getFavoriteEmployeeId(userId)
                .flatMap(repository::findById)
                .map(User::getName)
                .orElse(null);
    }


    public PageDTO<FullUserDTO> search(
            String name,
            String email,
            String authority,
            int page,
            int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> result = repository.search(name, email, authority, pageable);

        return new PageDTO<>(
                result.getContent().stream()
                        .map(mapper::toFullUserDTO)
                        .toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    public List<FullUserDTO> findAll() {
        return repository.findAll().stream().map(mapper::toFullUserDTO).toList();
    }

    public FullUserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);

        return mapper.toFullUserDTO(user);
    }

    public List<EmployeeDTO> findEmployees() {
        var users = repository.findByAuthorities_Authority(AUTHORITY_EMPLOYEE);

        return users.stream().map(mapper::toEmployeeDTO).toList();
    }

    public ProfileDTO updateProfile(Long id, UserPatchDTO dto) {
        patchUpdate(id, dto);

        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);

        return mapper.toProfileDTO(user);
    }

    public FullUserDTO patchUpdate(Long id, UserPatchDTO dto) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);

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

        return mapper.toFullUserDTO(repository.save(user));
    }

    private Authority getAuthority(String authority) {
        return authorityRepository.findById(authority).orElseThrow();
    }

}
