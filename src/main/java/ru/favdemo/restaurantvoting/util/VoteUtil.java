package ru.favdemo.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VoteUtil {

    public static List<VoteTo> getTos(List<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDateTime(), vote.getUser(), vote.getRestaurant());
    }
}
