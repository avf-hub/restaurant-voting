package ru.favdemo.restaurantvoting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    @Transactional
    public void update(Restaurant restaurant, int id) {
        repository.getExisted(id);
        repository.save(restaurant);
    }
}
