package ru.favdemo.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.model.User;

import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends AbstractBaseTo {

    LocalDateTime dateTime;

    User user;

    Restaurant restaurant;

    public VoteTo(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }
}
