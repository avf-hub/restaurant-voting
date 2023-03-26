package ru.favdemo.restaurantvoting.error;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {

    public NotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}