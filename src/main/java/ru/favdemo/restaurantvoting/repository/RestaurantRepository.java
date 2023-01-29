package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ru.favdemo.restaurantvoting.error.IllegalRequestDataException;
import ru.favdemo.restaurantvoting.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Optional<Restaurant> getWithMenu(int id);
}
