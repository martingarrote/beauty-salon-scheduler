package com.martingarrote.beauty_salon_scheduler.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByAuthorities_Authority(String authority);
    @Query("SELECT u FROM User u " +
            "JOIN u.authorities a " +
            "WHERE (:name IS NULL OR u.name LIKE %:name%) " +
            "AND (:email IS NULL OR u.email LIKE %:email%) " +
            "AND (:authority IS NULL OR a.authority LIKE %:authority%)")
    Page<User> search(
            @Param("name") String name,
            @Param("email") String email,
            @Param("authority") String authority,
            Pageable pageable
    );
}
