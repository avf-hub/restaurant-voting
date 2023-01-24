package ru.favdemo.restaurantvoting.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Vote extends AbstractBaseEntity {

    private LocalDate voteDate;

    private User user;

    private Restaurant restaurant;
}
