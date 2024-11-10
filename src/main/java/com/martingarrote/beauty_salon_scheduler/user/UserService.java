package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.authority.Authority;
import com.martingarrote.beauty_salon_scheduler.authority.AuthorityRepository;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailAlreadyInUseException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.WrongPasswordException;
import com.martingarrote.beauty_salon_scheduler.mapper.UserMapper;
import com.martingarrote.beauty_salon_scheduler.security.TokenService;
import com.martingarrote.beauty_salon_scheduler.user.dto.EmployeeDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.LoginDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.SignupDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.TokenDTO;
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

    public List<EmployeeDTO> findEmployees() {
        var users = repository.findByAuthorities_Authority(AUTHORITY_EMPLOYEE);

        return users.stream().map(mapper::toEmployeeDTO).toList();
    }

    private Authority getAuthority(String authority) {
        return authorityRepository.findById(authority).orElseThrow();
    }

}
