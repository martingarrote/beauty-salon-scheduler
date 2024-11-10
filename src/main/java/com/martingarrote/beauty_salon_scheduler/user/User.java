package com.martingarrote.beauty_salon_scheduler.user;

import com.martingarrote.beauty_salon_scheduler.authority.Authority;
import com.martingarrote.beauty_salon_scheduler.user.enums.FaceShape;
import com.martingarrote.beauty_salon_scheduler.user.enums.HairCurl;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private FaceShape faceShape;

    @Enumerated(EnumType.STRING)
    private HairCurl hairCurl;

    private String about;

    private String instagram;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority_junction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Authority> authorities;

    public List<SimpleGrantedAuthority> mapAuthorityToSimpleGrantedAuthority() {
        return authorities.stream().map(Authority::getAuthority).map(SimpleGrantedAuthority::new).toList();
    }
}
