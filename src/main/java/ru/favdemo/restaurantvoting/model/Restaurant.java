package ru.favdemo.restaurantvoting.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends AbstractNamedEntity {

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + name + ']';
    }
}
