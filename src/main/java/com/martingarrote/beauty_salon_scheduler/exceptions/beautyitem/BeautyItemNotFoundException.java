package com.martingarrote.beauty_salon_scheduler.exceptions.beautyitem;

public class BeautyItemNotFoundException extends RuntimeException {

    public BeautyItemNotFoundException() {super("The specified beauty item could not be found. Please check the item ID and try again.");}
    public BeautyItemNotFoundException(String message) {super(message);}

}
