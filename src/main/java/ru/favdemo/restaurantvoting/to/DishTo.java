package ru.favdemo.restaurantvoting.to;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends AbstractNamedTo {

    @NotNull
    LocalDate dishDate;

    @NotNull
    @DecimalMin(value = "1")
    BigDecimal price;

    public DishTo(Integer id, String name, LocalDate dishDate, BigDecimal price) {
        super(id, name);
        this.dishDate = dishDate;
        this.price = price;
    }
}
