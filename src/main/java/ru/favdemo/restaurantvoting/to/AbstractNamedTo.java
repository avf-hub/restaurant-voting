package ru.favdemo.restaurantvoting.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.favdemo.restaurantvoting.util.validation.NoHtml;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractNamedTo extends AbstractBaseTo {

    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    protected String name;

    public AbstractNamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}
