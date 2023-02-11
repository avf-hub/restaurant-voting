package ru.favdemo.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "restaurant",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("dishDate DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private List<Dish> menu;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + name + ']';
    }
}
