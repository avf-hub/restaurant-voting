package ru.favdemo.restaurantvoting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.repository.RestaurantRepository;
import ru.favdemo.restaurantvoting.repository.UserRepository;
import ru.favdemo.restaurantvoting.repository.VoteRepository;

import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

    public static LocalTime votingStopTime = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        vote.setUser(userRepository.getExisted(userId));
        vote.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return voteRepository.save(vote);
    }

    @Transactional
    public void update(Vote vote, int userId, int restaurantId) {
        if (LocalTime.now().isAfter(votingStopTime)) {
            throw new DataConflictException("You can`t change your vote after 11:00");
        }
        vote.setUser(userRepository.getExisted(userId));
        vote.setRestaurant(restaurantRepository.getExisted(restaurantId));
        voteRepository.save(vote);
    }
}
