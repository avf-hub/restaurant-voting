package ru.favdemo.restaurantvoting.web.dish;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.repository.DishRepository;
import ru.favdemo.restaurantvoting.service.DishService;
import ru.favdemo.restaurantvoting.to.DishTo;
import ru.favdemo.restaurantvoting.util.DishUtil;

import java.net.URI;
import java.util.List;

import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "Dish Controller")
public class AdminDishRestController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    private final DishRepository repository;
    private final DishService service;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get Dish {} for restaurant {}", id, restaurantId);
        return ResponseEntity.of(repository.get(id, restaurantId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete Dish {} for restaurant {}", id, restaurantId);
        repository.deleteExisted(id);
    }

    @GetMapping
    public List<DishTo> getAll(@PathVariable int restaurantId) {
        log.info("get all dishes by restaurant id {}", restaurantId);
        return DishUtil.getTos(repository.getAll(restaurantId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update Dish {} by restaurant id {}", dish, restaurantId);
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create dish {} by restaurant id {}", dish, restaurantId);
        checkNew(dish);
        Dish created = service.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL.replace("{restaurantId}", String.valueOf(restaurantId)) + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/filter")
    public List<DishTo> getAllToDay(@PathVariable int restaurantId) {
        log.info("get all dishes by restaurant id {} to day", restaurantId);
        return DishUtil.getTos(repository.getAllToDay(restaurantId));
    }
}
