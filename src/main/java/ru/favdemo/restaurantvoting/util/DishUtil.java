package ru.favdemo.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.to.DishTo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class DishUtil {

    public static List<DishTo> getTos(Collection<Dish> menu) {
        return filterByPredicate(menu, dish -> true);
    }

    public static List<DishTo> getFilteredTos(Collection<Dish> menu, LocalDate startDate, LocalDate endDate) {
        return filterByPredicate(menu, dish -> Util.isBetweenHalfOpen(dish.getDishDate(), startDate, endDate));
    }

    public static List<DishTo> filterByPredicate(Collection<Dish> menu, Predicate<Dish> filter) {
        return menu.stream()
                .filter(filter)
                .map(DishUtil::createTo)
                .collect(Collectors.toList());
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getDishDate(), dish.getPrice());
    }
}
