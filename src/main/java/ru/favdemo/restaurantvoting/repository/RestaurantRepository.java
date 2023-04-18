package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id=:id AND d.dishDate=current_date()")
    Optional<Restaurant> getWithDishesToDay(int id);

    @Query("SELECT r FROM Restaurant r JOIN r.dishes d WHERE d.dishDate=current_date() ORDER BY r.id ASC")
    List<Restaurant> getRestaurantsToDay();
}
