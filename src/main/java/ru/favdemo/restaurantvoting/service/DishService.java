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

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }

    @Transactional
    public void update(Dish dish, int restaurantId) {
        dishRepository.getExistedOrBelonged(dish.id(), restaurantId);
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        dishRepository.save(dish);
    }
}
