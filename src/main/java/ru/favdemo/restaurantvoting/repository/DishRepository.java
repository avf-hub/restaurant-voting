package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    @Query("SELECT d from Dish d WHERE d.restaurant.id=:restaurantId AND d.dishDate >= :startDate AND d.dishDate < :endDate ORDER BY d.dishDate DESC")
    List<Dish> getBetweenHalfOpen(LocalDate startDate, LocalDate endDate, int restaurantId);

    default Dish getExistedOrBelonged(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
