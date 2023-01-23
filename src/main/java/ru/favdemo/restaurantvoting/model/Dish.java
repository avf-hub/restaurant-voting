package ru.favdemo.restaurantvoting.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends AbstractBaseEntity {

    private LocalDateTime dateTime;

    private int price;

    private String description;

    private Restaurant restaurant;
}
