package ru.favdemo.restaurantvoting.error;

import org.springframework.http.HttpStatus;

public class UpdateVoteException extends AppException {

    public UpdateVoteException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
