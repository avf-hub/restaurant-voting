package ru.favdemo.restaurantvoting.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends AbstractBaseEntity {

    private LocalDate date = LocalDate.now();

    private List<Dish> dishes;

    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Menu[" +
                "id=" + id +
                ", date=" + date +
                ", dishes=" + dishes +
                ']';
    }
}
