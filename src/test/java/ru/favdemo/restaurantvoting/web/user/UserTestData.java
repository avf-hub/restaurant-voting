package ru.favdemo.restaurantvoting.web.user;

import ru.favdemo.restaurantvoting.model.Role;
import ru.favdemo.restaurantvoting.model.User;
import ru.favdemo.restaurantvoting.util.JsonUtil;
import ru.favdemo.restaurantvoting.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.favdemo.restaurantvoting.web.vote.VoteTestData.*;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "votes", "password");
    public static MatcherFactory.Matcher<User> USER_WITH_VOTES_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "votes.user", "votes.restaurant", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int ADMIN_ID = 100000;
    public static final int USER_ID = 100001;
    public static final int USER_VOTE2 = 100003;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@gmail.com";
    public static final String USER_VOTE1_MAIL = "user1@gmail.com";
    public static final String USER_VOTE2_MAIL = "user2@gmail.com";
    public static final String ADMIN_MAIL = "admin@gmail.com";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User user_vote1 = new User(USER_ID + 1, "User-vote1", USER_VOTE1_MAIL, "password1", Role.USER);
    public static final User user_vote2 = new User(USER_ID + 2, "User-vote2", USER_VOTE2_MAIL, "password2", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);

    static {
        user.setVotes(List.of(VOTE_2));
        user_vote1.setVotes(List.of(VOTE_3));
        user_vote2.setVotes(List.of(VOTE_1));
        user_vote2.setVotes(List.of(VOTE_4));
        user_vote2.setVotes(List.of(VOTE_5));
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), List.of(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
