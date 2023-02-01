package ru.favdemo.restaurantvoting.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends AbstractNamedTo {

    @NotNull
    LocalDate dishDate;

    @NotNull
    @Size(min = 10, max = 2000)
    Double price;

    public DishTo(Integer id, String name, LocalDate dishDate, Double price) {
        super(id, name);
        this.dishDate = dishDate;
        this.price = price;
    }
}
