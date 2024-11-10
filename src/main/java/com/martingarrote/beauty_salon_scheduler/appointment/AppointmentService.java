package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.appointment.dto.AppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.appointment.dto.CreationAppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItemRepository;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.UserNotFoundException;
import com.martingarrote.beauty_salon_scheduler.mapper.AppointmentMapper;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        Appointment appointment = Appointment.builder()
                .employee(employee)
                .customer(customer)
                .dateTime(dto.dateTime())
                .beautyItems(beautyItems)
                .observations(dto.observations())
                .totalPrice(totalPrice)
                .build();

        return mapper.toDTO(repository.save(appointment));
    }

}
