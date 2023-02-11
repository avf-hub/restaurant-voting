package ru.favdemo.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "dish",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "dish_date", "restaurant_id"}, name = "dish_unique_name_dishdate_restaurant_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends AbstractNamedEntity {

    @Column(name = "dish_date")
    @NotNull
    private LocalDate dishDate;

    @Column(name = "price", nullable = false)
    @NotNull
    @DecimalMin(value = "10.")
    @DecimalMax(value = "3000.")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @Schema(hidden = true)
    private Restaurant restaurant;

    public Dish(Integer id, String name, LocalDate dishDate, Double price) {
        super(id, name);
        this.dishDate = dishDate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish:" + id + '[' + name + " - " + price + ']';
    }
}
