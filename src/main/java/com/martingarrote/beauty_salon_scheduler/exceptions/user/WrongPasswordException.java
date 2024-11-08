package com.martingarrote.beauty_salon_scheduler.exceptions.user;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {super("The password entered is incorrect. Please try again.");}
    public WrongPasswordException(String message) {super(message);}

}
