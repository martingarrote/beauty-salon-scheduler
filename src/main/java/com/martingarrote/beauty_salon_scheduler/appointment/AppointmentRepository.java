package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerIdAndStartIntervalBefore(Long customerId, LocalDateTime startInterval);
    List<Appointment> findByCustomerIdAndStartIntervalAfter(Long customerId, LocalDateTime startInterval);
    boolean existsByCustomerIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
            Long userId, LocalDateTime endInterval, LocalDateTime startInterval);
    boolean existsByEmployeeIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
            Long userId, LocalDateTime endInterval, LocalDateTime startInterval);
    @Query("""
    SELECT COUNT(bi.id)
    FROM Appointment a
    JOIN a.beautyItems bi
    WHERE a.customer.id = :userId
    """)
    int countLoyaltyPointsByUserId(@Param("userId") Long userId);

    @Query("""
    SELECT a.employee.id
    FROM Appointment a
    WHERE a.customer.id = :userId
    GROUP BY a.employee.id
    ORDER BY COUNT(a.employee.id) DESC
    LIMIT 1
    """)
    Long findFavoriteEmployeeIdByUserId(@Param("userId") Long userId);


}
