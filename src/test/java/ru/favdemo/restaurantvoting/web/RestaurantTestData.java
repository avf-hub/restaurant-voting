package ru.favdemo.restaurantvoting.web;

import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.to.RestaurantTo;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import static ru.favdemo.restaurantvoting.web.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT_ID = 100004;
    public static final int NOT_FOUND = 100;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Restaurant-1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Restaurant-2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "Restaurant-3");

    static {
        RESTAURANT_1.setDishes(dishes_1);
        RESTAURANT_2.setDishes(dishes_2);
        RESTAURANT_3.setDishes(dishes_3);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Созданный ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Обновленный ресторан");
    }
}
