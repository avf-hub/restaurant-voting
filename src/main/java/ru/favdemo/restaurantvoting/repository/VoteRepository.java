package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    Optional<Vote> get(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> getAllByUser(int userId);

    @Query("SELECT v FROM Vote v WHERE v.dateTime=:dateTime AND v.user.id=:userId")
    Optional<Vote> getOnDate(int userId, LocalDateTime dateTime);

    default Vote getExistedOrBelonged(int userId, LocalDateTime dateTime) {
        return getOnDate(userId, dateTime).orElseThrow(
                () -> new DataConflictException("For User id=" + userId + " vote is not exist or doesn't belong on voteDate=" + dateTime));
    }
}
