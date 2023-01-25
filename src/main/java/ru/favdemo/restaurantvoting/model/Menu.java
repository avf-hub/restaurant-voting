package ru.favdemo.restaurantvoting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menu",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "menu_date"}, name = "menu_unique_restaurant_menudate_idx")},
        indexes = {@Index(columnList = "menu_date", name = "menu_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu extends AbstractBaseEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate menuDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "menu_dish",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private Set<Dish> dishes;

    @Override
    public String toString() {
        return "Menu:" + id + '[' + menuDate + " - " + restaurant + ']';
    }
}
