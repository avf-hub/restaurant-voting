package ru.favdemo.restaurantvoting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.favdemo.restaurantvoting.error.IllegalRequestDataException;
import ru.favdemo.restaurantvoting.model.Vote;
import ru.favdemo.restaurantvoting.repository.VoteRepository;
import ru.favdemo.restaurantvoting.util.JsonUtil;
import ru.favdemo.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.favdemo.restaurantvoting.web.user.UserTestData.USER_VOTE2;
import static ru.favdemo.restaurantvoting.web.user.UserTestData.USER_VOTE2_MAIL;
import static ru.favdemo.restaurantvoting.web.vote.ProfileVoteRestController.REST_URL;
import static ru.favdemo.restaurantvoting.web.vote.VoteTestData.*;

class ProfileVoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE_ID))
                .andExpect(status().isNoContent());
        assertThrows(IllegalRequestDataException.class, () -> repository.getExisted(VOTE_ID));
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void deleteDataConflict() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE_NOT_FOUND))
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void update() throws Exception {
        Vote updated = VoteTestData.getUpdated();
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } else {
            perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(status().isNoContent());

            VOTE_MATCHER.assertMatch(repository.getExistedOrBelonged(VOTE_ID, USER_VOTE2), updated);
        }
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)));

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void createInvalid() throws Exception {
        Vote invalid = new Vote(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void updateInvalid() throws Exception {
        Vote invalid = new Vote(VOTE_ID, null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void getOnDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "/vote-today"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_1));
    }

    @Test
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void createDuplicate() throws Exception {
        Vote invalid = new Vote(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(value = USER_VOTE2_MAIL)
    void updateDuplicate() throws Exception {
        Vote invalid = new Vote(VOTE_ID, null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}