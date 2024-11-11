package com.martingarrote.beauty_salon_scheduler.exceptions.appointment;

public class AppointmentOverlapException extends RuntimeException {

    public AppointmentOverlapException() {super("This time slot is already booked.");}
    public AppointmentOverlapException(String message) {super(message);}

}
