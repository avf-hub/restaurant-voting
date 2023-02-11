package ru.favdemo.restaurantvoting.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public abstract class AbstractBaseTo {

    @Schema(hidden = true)
    protected Integer id;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
