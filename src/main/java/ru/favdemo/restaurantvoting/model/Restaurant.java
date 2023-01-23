package ru.favdemo.restaurantvoting.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

    private List<Dish> dishes;

    private Set<Vote> votes;

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + name + ']';
    }
}
