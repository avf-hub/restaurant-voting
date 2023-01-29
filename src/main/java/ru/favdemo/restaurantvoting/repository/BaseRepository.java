package ru.favdemo.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.IllegalRequestDataException;
import ru.favdemo.restaurantvoting.error.NotFoundException;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(int id);

    default void deleteExisted(int id) {
        if (delete(id) == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id")
    T get(int id);

    default T getExisted(int id) {
        return findById(id).orElseThrow(() -> new IllegalRequestDataException("Entity with id=" + id + " not found"));
    }
}
