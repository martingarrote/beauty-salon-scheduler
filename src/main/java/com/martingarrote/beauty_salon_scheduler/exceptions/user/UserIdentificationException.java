package com.martingarrote.beauty_salon_scheduler.exceptions.user;

public class UserIdentificationException extends RuntimeException {

    public UserIdentificationException() {super("Unable to identify the user based on the authentication information in the request.");}
    public UserIdentificationException(String message) {super(message);}

}
