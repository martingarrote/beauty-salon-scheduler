package com.martingarrote.beauty_salon_scheduler.exceptions.user;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {super("No account found with the provided email address.");}
    public EmailNotFoundException(String message) {super(message);}
}