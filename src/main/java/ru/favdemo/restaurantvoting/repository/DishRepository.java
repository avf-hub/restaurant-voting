package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.dishDate DESC")
    List<Dish> getAll(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.dishDate=current_date() ORDER BY d.name")
    List<Dish> getAllToDay(int restaurantId);

    default void getExistedOrBelonged(int id, int restaurantId) {
        get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
