package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ru.favdemo.restaurantvoting.error.DataConflictException;
import ru.favdemo.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    Optional<Vote> get(int id, int userId);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.voteDate=:voteDate AND v.user.id=:userId")
    Optional<Vote> getOnDate(int userId, LocalDate voteDate);

    default Vote getExistedOrBelonged(int id, int userId) {
        return get(id, userId).orElseThrow(
                ()-> new DataConflictException("Vote id=" + id + " is not exist or doesn't belong to User id=" + userId)
        );
    }
}
