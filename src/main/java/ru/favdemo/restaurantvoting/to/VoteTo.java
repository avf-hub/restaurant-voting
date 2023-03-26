package ru.favdemo.restaurantvoting.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.model.User;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends AbstractBaseTo {

    @NotNull
    User user;

    @NotNull
    Restaurant restaurant;

    public VoteTo(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }
}
