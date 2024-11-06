package com.martingarrote.beauty_salon_scheduler.exceptions.beautyitem;

public class BeautyItemNotFoundException extends RuntimeException {

    public BeautyItemNotFoundException() {super("Beauty item not found.");}
    public BeautyItemNotFoundException(String message) {super(message);}

}
