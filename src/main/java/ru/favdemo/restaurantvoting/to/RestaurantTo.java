package ru.favdemo.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.favdemo.restaurantvoting.model.Dish;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends AbstractNamedTo {

    List<DishTo> menu;

    public RestaurantTo(Integer id, String name, List<DishTo> menu) {
        super(id, name);
        this.menu = menu;
    }
}
