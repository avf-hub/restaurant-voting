package ru.favdemo.restaurantvoting.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.repository.RestaurantRepository;
import ru.favdemo.restaurantvoting.to.RestaurantTo;
import ru.favdemo.restaurantvoting.util.RestaurantUtil;

import java.net.URI;
import java.util.List;

import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantRestController {

    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        Restaurant restaurant = repository.getExisted(id);
        repository.delete(restaurant);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return RestaurantUtil.getTos(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        repository.getExisted(id);
        repository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/menu")
    public ResponseEntity<Restaurant> getWithMenu(@PathVariable int id) {
        return ResponseEntity.of(repository.getWithMenu(id));
    }
}
