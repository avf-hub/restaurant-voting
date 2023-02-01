package ru.favdemo.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.to.DishTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DishUtil {

    public static List<DishTo> getTos(List<Dish> menu) {
        return menu.stream()
                .map(DishUtil::createTo)
                .collect(Collectors.toList());
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getDishDate(), dish.getPrice());
    }
}
