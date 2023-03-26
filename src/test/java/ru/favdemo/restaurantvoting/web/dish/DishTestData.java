package ru.favdemo.restaurantvoting.web.dish;

import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.to.DishTo;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingEqualsComparator(DishTo.class);

    public static final int DISH_ID = 100007;
    public static final int NOT_FOUND = 100;

    public static final Dish dish_1 = new Dish(DISH_ID, "Борщ", LocalDate.now(), new BigDecimal("100.00"));
    public static final Dish dish_2 = new Dish(DISH_ID + 1, "Суп харчо", LocalDate.now(), new BigDecimal("120.00"));
    public static final Dish dish_3 = new Dish(DISH_ID + 2, "Сырный суп", LocalDate.now(), new BigDecimal("150.00"));
    public static final Dish dish_4 = new Dish(DISH_ID + 3, "Котлеты по киевски", LocalDate.now(), new BigDecimal("600.00"));
    public static final Dish dish_5 = new Dish(DISH_ID + 4, "Куриная отбивная", LocalDate.now(), new BigDecimal("400.00"));
    public static final Dish dish_6 = new Dish(DISH_ID + 5, "Стейк", LocalDate.now(), new BigDecimal("1900.00"));
    public static final Dish dish_7 = new Dish(DISH_ID + 6, "Компот из вишни", LocalDate.now(), new BigDecimal("150.00"));
    public static final Dish dish_8 = new Dish(DISH_ID + 7, "Апельсиновый сок", LocalDate.now(), new BigDecimal("110.00"));
    public static final Dish dish_9 = new Dish(DISH_ID + 8, "Черный чай", LocalDate.now(), new BigDecimal("50.00"));
    public static final Dish dish_10 = new Dish(DISH_ID + 9, "Пюре", LocalDate.now(), new BigDecimal("35.00"));
    public static final Dish dish_11 = new Dish(DISH_ID + 10, "Рис", LocalDate.now(), new BigDecimal("15.00"));
    public static final Dish dish_12 = new Dish(DISH_ID + 11, "Гречка", LocalDate.now(), new BigDecimal("10.00"));

    public static final List<Dish> dishes_1 = List.of(dish_1, dish_4, dish_9, dish_10);
    public static final List<Dish> dishes_2 = List.of(dish_3, dish_6, dish_7, dish_12);
    public static final List<Dish> dishes_3 = List.of(dish_2, dish_5, dish_8, dish_11);
    public static final List<Dish> dishesToDay = List.of(dish_1, dish_4, dish_10, dish_9);

    public static Dish getNew() {
        return new Dish(null, "Созданное блюдо", LocalDate.now(), new BigDecimal(1445));
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Обновленное блюдо", dish_1.getDishDate().plus(1, ChronoUnit.DAYS), new BigDecimal(200));
    }
}
