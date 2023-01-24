package ru.favdemo.restaurantvoting.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Menu extends AbstractBaseEntity {

    private LocalDate menuDate;

    private Restaurant restaurant;

    private Set<Dish> dishes;
}
