package com.martingarrote.beauty_salon_scheduler.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerIdAndStartIntervalBefore(Long customerId, LocalDateTime startInterval);
    List<Appointment> findByCustomerIdAndStartIntervalAfter(Long customerId, LocalDateTime startInterval);
    boolean existsByCustomerIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
            Long userId, LocalDateTime endInterval, LocalDateTime startInterval);
    boolean existsByEmployeeIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
            Long userId, LocalDateTime endInterval, LocalDateTime startInterval);
}
