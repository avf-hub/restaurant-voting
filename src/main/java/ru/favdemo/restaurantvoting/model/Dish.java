package ru.favdemo.restaurantvoting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "dish",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_date", "name"}, name = "dish_unique_restaurant_dishdate_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Dish extends AbstractNamedEntity {

    @Column(name = "dish_date")
    @NotNull
    private LocalDate dishDate;

    @Column(name = "price", nullable = false)
    @NotNull
    @Size(min = 10, max = 2000)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Dish:" + id + '[' + name + " - " + price + ']';
    }
}
