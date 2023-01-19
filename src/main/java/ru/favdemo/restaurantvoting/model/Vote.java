package ru.favdemo.restaurantvoting.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends AbstractBaseEntity {

    private LocalDate voteDate = LocalDate.now();

    private User user;

    private Restaurant restaurant;
}
