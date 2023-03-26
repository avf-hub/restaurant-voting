package ru.favdemo.restaurantvoting.web.vote;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.favdemo.restaurantvoting.model.Restaurant;
import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.repository.RestaurantRepository;
import ru.favdemo.restaurantvoting.repository.VoteRepository;
import ru.favdemo.restaurantvoting.service.VoteService;
import ru.favdemo.restaurantvoting.to.RestaurantTo;
import ru.favdemo.restaurantvoting.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.favdemo.restaurantvoting.util.RestaurantUtil.getTos;
import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.favdemo.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ProfileVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "User voting Controller")
public class ProfileVoteRestController {

    static final String REST_URL = "/api/profile/voting";

    private final VoteRepository voteRepository;
    private final VoteService voteService;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get Vote id {} for user id {}", id, authUser.id());
        return ResponseEntity.of(voteRepository.get(id, authUser.id()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Vote vote, @AuthenticationPrincipal AuthUser authUser,
                       @PathVariable int id) {
        log.info("update Vote {} for user id {}", vote, authUser.id());
        assureIdConsistent(vote, id);
        voteService.update(vote, authUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote, @AuthenticationPrincipal AuthUser authUser) {
        log.info("create Vote {} for user id {}", vote, authUser.id());
        checkNew(vote);
        Vote created = voteService.save(vote, authUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/vote-today")
    public ResponseEntity<Vote> getToDay(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote for user id {} to day", authUser.id());
        return ResponseEntity.of(voteRepository.getToDay(authUser.id()));
    }

    @GetMapping()
    public List<RestaurantTo> getRestaurantsToDay() {
        log.info("get restaurants for voting");
        return getTos(restaurantRepository.getRestaurantsToDay());
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getWithDishesToDay(@PathVariable int restaurantId) {
        log.info("get restaurant with dishes {}", restaurantId);
        return ResponseEntity.of(restaurantRepository.getWithDishesToDay(restaurantId));
    }
}
