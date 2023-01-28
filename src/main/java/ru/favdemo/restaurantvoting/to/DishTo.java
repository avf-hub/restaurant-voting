package ru.favdemo.restaurantvoting.to;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends AbstractNamedTo {

    @NotNull
    LocalDate dishDate;

    @NotNull
    @Range(min = 10, max = 2000)
    Double price;

    public DishTo(Integer id, String name, LocalDate dishDate, Double price) {
        super(id, name);
        this.dishDate = dishDate;
        this.price = price;
    }
}
