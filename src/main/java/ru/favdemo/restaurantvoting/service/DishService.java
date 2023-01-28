package ru.favdemo.restaurantvoting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.repository.DishRepository;
import ru.favdemo.restaurantvoting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class DishService {

    private DishRepository dishRepository;
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}
