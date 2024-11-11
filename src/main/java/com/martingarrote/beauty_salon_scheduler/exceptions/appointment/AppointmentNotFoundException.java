package com.martingarrote.beauty_salon_scheduler.exceptions.appointment;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException() {super("The specified appointment could not be found.");}
    public AppointmentNotFoundException(String message) {super(message);}

}
