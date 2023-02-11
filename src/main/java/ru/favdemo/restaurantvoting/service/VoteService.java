package ru.favdemo.restaurantvoting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.error.UpdateVoteException;
import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.repository.UserRepository;
import ru.favdemo.restaurantvoting.repository.VoteRepository;

import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

    public static LocalTime UPDATE_DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        vote.setUser(userRepository.getExisted(userId));
        return voteRepository.save(vote);
    }

    @Transactional
    public void update(Vote vote, int userId) {
        if (LocalTime.now().isAfter(UPDATE_DEADLINE)) {
            throw new UpdateVoteException("You can`t change your vote after 11:00");
        }
        vote.setUser(userRepository.getExisted(userId));
        voteRepository.save(vote);
    }
}
