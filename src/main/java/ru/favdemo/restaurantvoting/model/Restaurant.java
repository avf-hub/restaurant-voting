package ru.favdemo.restaurantvoting.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

    private List<Menu> menus;

    private List<Vote> votes;;

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + name + ']';
    }
}
