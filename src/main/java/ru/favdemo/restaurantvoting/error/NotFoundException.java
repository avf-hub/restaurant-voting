package ru.favdemo.restaurantvoting.error;

public class NotFoundException extends AppException {

    public NotFoundException(String msg) {
        super(msg);
    }
}