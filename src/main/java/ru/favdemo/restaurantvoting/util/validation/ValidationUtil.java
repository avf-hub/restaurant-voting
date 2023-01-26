package ru.favdemo.restaurantvoting.util.validation;

import lombok.experimental.UtilityClass;
import ru.favdemo.restaurantvoting.error.IllegalRequestDataException;

@UtilityClass
public class ValidationUtil {

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }

    public static <T> T checkExisted(T obj, int id) {
        if (obj == null) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
        return obj;
    }
}
