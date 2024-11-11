package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDateTime startInterval;

    private LocalDateTime endInterval;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "appointment_beauty_item_junction",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "beauty_item_id")
    )
    private List<BeautyItem> beautyItems;

    private boolean active;

    private Double totalPrice;

    private String observations;

}
