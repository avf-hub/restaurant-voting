package ru.favdemo.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends AbstractNamedTo {

    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }
}
