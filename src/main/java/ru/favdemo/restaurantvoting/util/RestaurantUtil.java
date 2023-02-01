package ru.favdemo.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantTo> getTos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }
}
