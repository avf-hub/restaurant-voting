package ru.favdemo.restaurantvoting.web.vote;

import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static ru.favdemo.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_1;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int VOTE_ID = 100019;
    public static final int VOTE_NOT_FOUND = 100;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, LocalDate.now());
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, LocalDate.now());
    public static final Vote VOTE_3 = new Vote(VOTE_ID + 2, LocalDate.now());
    public static final Vote VOTE_4 = new Vote(VOTE_ID + 3, LocalDate.parse("2023-02-05"));
    public static final Vote VOTE_5 = new Vote(VOTE_ID + 4, LocalDate.parse("2023-01-28"));

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getUpdated() {
        Vote newVote = new Vote(VOTE_ID, LocalDate.now().plus(1, ChronoUnit.DAYS));
        newVote.setRestaurant(RESTAURANT_1);
        return newVote;
    }
}
