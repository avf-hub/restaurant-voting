package ru.favdemo.restaurantvoting.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Vote extends AbstractBaseEntity {

    private LocalDate voteDate = LocalDate.now();

    private User user;

    private Restaurant restaurant;
}
