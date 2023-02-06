package ru.favdemo.restaurantvoting.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.model.Dish;
import ru.favdemo.restaurantvoting.repository.DishRepository;
import ru.favdemo.restaurantvoting.util.JsonUtil;
import ru.favdemo.restaurantvoting.web.AbstractControllerTest;
import ru.favdemo.restaurantvoting.web.user.UserTestData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.favdemo.restaurantvoting.util.DishUtil.*;
import static ru.favdemo.restaurantvoting.web.dish.AdminDishRestController.REST_URL;
import static ru.favdemo.restaurantvoting.web.dish.DishTestData.*;
import static ru.favdemo.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ru.favdemo.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;

class AdminDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL_REPLACE = REST_URL.replace("{restaurantId}", String.valueOf(RESTAURANT_ID));
    private static final String REST_URL_SLASH_1 = REST_URL_REPLACE + '/';
    private static final String REST_URL_SLASH_2 = REST_URL.replace("{restaurantId}", String.valueOf(RESTAURANT_ID + 1)) + '/';

    @Autowired
    private DishRepository dishRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_1 + DISH_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish_1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_1 + DISH_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_1 + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH_1 + DISH_ID))
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.get(DISH_ID, UserTestData.USER_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteDataConflict() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH_2 + DISH_ID))
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH_1 + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishRepository.getExisted(DISH_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_REPLACE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.getExisted(newId), newDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_REPLACE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHER.contentJson(getTos(menu_1)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_1 + "filter")
                .param("startDate", LocalDate.now().plusDays(-1).toString())
                .param("endDate", LocalDate.now().plusDays(1).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(DISH_TO_MATCHER.contentJson(createTo(dish_1), createTo(dish_4), createTo(dish_10), createTo(dish_9)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getBetweenAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_1 + "filter?startDate=&endTime="))
                .andExpect(status().isOk())
                .andExpect(DISH_TO_MATCHER.contentJson(getTos(menu_1)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, "Dummy", LocalDate.now(), 100.);
        perform(MockMvcRequestBuilders.post(REST_URL_REPLACE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_ID, null, null, 6000.);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH_1 + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateHtmlUnsafe() throws Exception {
        Dish invalid = new Dish(DISH_ID, "<script>alert(123)</script>", LocalDate.now(), 200.);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH_1 + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(value = ADMIN_MAIL)
    void updateDuplicate() throws Exception {
        Dish invalid = new Dish(DISH_ID, "Dummy", dish_2.getDishDate(), 200.);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH_1 + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createDuplicate() throws Exception {
        Dish invalid = new Dish(null, "Dummy", dish_7.getDishDate(), 200.);
        perform(MockMvcRequestBuilders.post(REST_URL_REPLACE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}