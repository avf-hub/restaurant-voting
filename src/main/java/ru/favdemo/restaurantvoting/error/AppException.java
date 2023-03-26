package ru.favdemo.restaurantvoting.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AppException extends ResponseStatusException {

    public AppException(HttpStatus status, @NonNull String message) {
        super(status, message);
    }

    @Override
    public String getMessage() {
        return super.getReason();
    }
}
