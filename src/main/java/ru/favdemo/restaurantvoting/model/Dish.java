package ru.favdemo.restaurantvoting.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends AbstractNamedEntity {

    private int price;

    private String description;

    private Menu menu;

    @Override
    public String toString() {
        return "Dish[" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", price='" + price +
                ", description='" + description +
                ']';
    }
}
