package ru.favdemo.restaurantvoting.web.restaurant;

import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.to.RestaurantTo;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import static ru.favdemo.restaurantvoting.web.dish.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT_ID = 100004;
    public static final int NOT_FOUND = 100;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Restaurant-1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Restaurant-2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "Restaurant-3");

    static {
        RESTAURANT_1.setMenu(menu_1);
        RESTAURANT_2.setMenu(menu_2);
        RESTAURANT_3.setMenu(menu_3);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Созданный ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Обновленный ресторан");
    }
}
