package ru.favdemo.restaurantvoting.web.dish;

import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.to.DishTo;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingEqualsComparator(DishTo.class);

    public static final int DISH_ID = 100007;
    public static final int NOT_FOUND = 100;

    public static final Dish dish_1 = new Dish(DISH_ID, "Борщ", LocalDate.now(), 100.);
    public static final Dish dish_2 = new Dish(DISH_ID + 1, "Суп харчо", LocalDate.now(), 120.);
    public static final Dish dish_3 = new Dish(DISH_ID + 2, "Сырный суп", LocalDate.now(), 150.);
    public static final Dish dish_4 = new Dish(DISH_ID + 3, "Котлеты по киевски", LocalDate.now(), 600.);
    public static final Dish dish_5 = new Dish(DISH_ID + 4, "Куриная отбивная", LocalDate.now(), 400.);
    public static final Dish dish_6 = new Dish(DISH_ID + 5, "Стейк", LocalDate.now(), 1900.);
    public static final Dish dish_7 = new Dish(DISH_ID + 6, "Компот из вишни", LocalDate.now(), 150.);
    public static final Dish dish_8 = new Dish(DISH_ID + 7, "Апельсиновый сок", LocalDate.now(), 110.);
    public static final Dish dish_9 = new Dish(DISH_ID + 8, "Черный чай", LocalDate.now(), 50.);
    public static final Dish dish_10 = new Dish(DISH_ID + 9, "Пюре", LocalDate.now(), 35.);
    public static final Dish dish_11 = new Dish(DISH_ID + 10, "Рис", LocalDate.now(), 15.);
    public static final Dish dish_12 = new Dish(DISH_ID + 11, "Рис", LocalDate.now(), 10.);

    public static final List<Dish> menu_1 = List.of(dish_1, dish_4, dish_9, dish_10);
    public static final List<Dish> menu_2 = List.of(dish_3, dish_6, dish_7, dish_12);
    public static final List<Dish> menu_3 = List.of(dish_2, dish_5, dish_8, dish_11);

    public static Dish getNew() {
        return new Dish(null, "Созданное блюдо", LocalDate.now(), 1445.);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Обновленное блюдо", dish_1.getDishDate().plus(1, ChronoUnit.DAYS), 200.);
    }
}
