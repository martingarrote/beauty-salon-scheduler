package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.appointment.dto.AppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.appointment.dto.CreationAppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItemRepository;
import com.martingarrote.beauty_salon_scheduler.exceptions.appointment.AppointmentOverlapException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.UserNotFoundException;
import com.martingarrote.beauty_salon_scheduler.mapper.AppointmentMapper;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final UserRepository userRepository;
    private final BeautyItemRepository beautyItemRepository;
    private final AppointmentMapper mapper = AppointmentMapper.INSTANCE;

    public AppointmentDTO create(CreationAppointmentDTO dto, Long userId) {
        User customer = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User employee = userRepository.findById(dto.employeeId()).orElseThrow(UserNotFoundException::new);

        List<BeautyItem> beautyItems = beautyItemRepository.findAllById(dto.beautyItemIds());

        Double totalPrice = beautyItems.stream().mapToDouble(BeautyItem::getPrice).sum();

        long totalDuration = beautyItems.stream().mapToLong(BeautyItem::getDuration).sum();
        System.out.println(totalDuration);

        LocalDateTime endInterval = dto.startInterval().plusMinutes(totalDuration);

        if (repository.existsByEmployeeIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
                userId, endInterval, dto.startInterval()
        )) {
            throw new AppointmentOverlapException("The selected employee already has an appointment scheduled for this time.");
        }

        if (repository.existsByCustomerIdAndStartIntervalLessThanAndEndIntervalGreaterThan(
                userId, endInterval, dto.startInterval()
        )) {
            throw new AppointmentOverlapException("You already have an appointment scheduled for this time.");
        }

        Appointment appointment = Appointment.builder()
                .employee(employee)
                .customer(customer)
                .startInterval(dto.startInterval())
                .endInterval(endInterval)
                .beautyItems(beautyItems)
                .observations(dto.observations())
                .totalPrice(totalPrice)
                .active(true)
                .build();

        return mapper.toDTO(repository.save(appointment));
    }

}