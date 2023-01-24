package ru.favdemo.restaurantvoting.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends AbstractBaseEntity {

    private int price;

    private String description;

    private Menu menu;
}
