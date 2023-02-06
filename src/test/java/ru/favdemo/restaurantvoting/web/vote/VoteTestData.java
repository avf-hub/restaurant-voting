package ru.favdemo.restaurantvoting.web.vote;

import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.to.VoteTo;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import java.time.LocalDateTime;

import static ru.favdemo.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static ru.favdemo.restaurantvoting.web.user.UserTestData.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user");
    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static final int VOTE_ID = 100019;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, LocalDateTime.now());
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, LocalDateTime.now());
    public static final Vote VOTE_3 = new Vote(VOTE_ID + 2, LocalDateTime.now());

    static {
        VOTE_1.setUser(user_vote2);
        VOTE_1.setRestaurant(RESTAURANT_1);
        VOTE_2.setUser(user);
        VOTE_2.setRestaurant(RESTAURANT_2);
        VOTE_3.setUser(user_vote1);
        VOTE_3.setRestaurant(RESTAURANT_3);
    }

    public static Vote getNew() {
        return new Vote(null, LocalDateTime.now());
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, LocalDateTime.now());
    }
}
