package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static com.javarush.jira.common.util.JsonUtil.writeValue;
import static com.javarush.jira.login.internal.web.UserTestData.USER_MAIL;
import static com.javarush.jira.profile.internal.web.ProfileTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {

    private final static String URL = ProfileRestController.REST_URL;

    // GET api/profile

    //success
    @Test
    @WithUserDetails(USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeValue(USER_PROFILE_TO)));
    }

    //fail
    @Test
    void getWithoutAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isUnauthorized());
    }

    //PUT api/profile

    //success
    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTo())))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    //fail no auth
    @Test
    void updateProfileNoAuth() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTo())))
                .andExpect(status().isUnauthorized());
    }

    //fail invalid update
    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfileInvalidData() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getInvalidTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    //fail unknown notification
    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfileUnknownNotification() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithUnknownNotificationTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    //fail unknown contact
    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfileUnknownContact() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithUnknownContactTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    //fail unsafe HTML
    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfileUnsafeHTML() throws Exception{
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithContactHtmlUnsafeTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}