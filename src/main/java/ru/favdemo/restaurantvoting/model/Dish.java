package ru.favdemo.restaurantvoting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Entity
@Table(name = "dish",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "price"}, name = "dish_unique_name_price_idx")},
        indexes = {@Index(columnList = "name", name = "dish_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 10, max = 2000)
    private Double price;

    @ManyToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    @OrderBy("menuDate DESC")
    private Set<Menu> menus;

    @Override
    public String toString() {
        return "Dish:" + id + '[' + name + " - " + price + ']';
    }
}
