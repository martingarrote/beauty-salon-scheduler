package com.martingarrote.beauty_salon_scheduler.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {super("No user found with the provided ID.");}
    public UserNotFoundException(String message) {super(message);}
}
