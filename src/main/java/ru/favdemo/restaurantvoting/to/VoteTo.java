package ru.favdemo.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.model.User;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends AbstractBaseTo {

    User user;

    Restaurant restaurant;

    public VoteTo(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }
}
