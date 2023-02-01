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
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v WHERE v.dateTime=:dateTime AND v.user.id=:userId")
    Optional<Vote> getOnDate(int userId, LocalDateTime dateTime);

    default Vote getExistedOrBelonged(int id, int userId) {
        return get(id, userId).orElseThrow(
                ()-> new DataConflictException("Vote id=" + id + " is not exist or doesn't belong to User id=" + userId)
        );
    }
}
