package com.martingarrote.beauty_salon_scheduler.exceptions.user;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException() {super("The email address is already in use. Please use a different email address.");}
    public EmailAlreadyInUseException(String message) {super(message);}
}
