package com.epam.learning.aykorenev.webservices.controller;

import com.epam.learning.aykorenev.webservices.model.User;
import com.epam.learning.aykorenev.webservices.services.UserService;
import com.epam.learning.aykorenev.webservices.services.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_get_all_users() throws Exception {
        List<User> users = UserUtils.createThreeUsers();
        given(service.getAll()).
                willReturn(users);

        mockMvc.perform(get("/users/").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$[0]").isMap()).
                andExpect(jsonPath("$[1]").isMap()).
                andExpect(jsonPath("$[1]").isMap()).
                andExpect(jsonPath("$[0].firstName").value("John")).
                andExpect(jsonPath("$[1].firstName").value("Bill")).
                andExpect(jsonPath("$[2].firstName").value("Henry"));

        verify(service, times(1)).getAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void should_create_user() throws Exception {
        String userXml = "<User>\n" +
                "<firstName>Petyr</firstName>\n" +
                "<lastName>Baelish</lastName>\n" +
                "<login>Littlefinger</login>\n" +
                "<email>littlefinger@email.com</email>\n" +
                "</User>";
        mockMvc.perform(post("/users/").
                contentType(MediaType.APPLICATION_XML).
                content(userXml)).
                andExpect(status().isCreated());

        verify(service, times(1)).createUser(any());
        verifyNoMoreInteractions(service);
    }

    public void should_update_user() throws Exception {
        String userJson = "{" +
                "\"firstName\":\"Tyrion\"," +
                "\"lastName\":\"Lan1ster\"," +
                "\"login\":\"superImp\"," +
                "\"email\":\"imp2017@email.com\"}";

        mockMvc.perform(put("/users/3").
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().isOk());

        verify(service, times(1)).updateUser(any());
        verifyNoMoreInteractions(service);
    }

    public void should_delete_user() throws Exception {
        mockMvc.perform(delete("/users/1").
                contentType(MediaType.ALL)).
                andExpect(status().isOk());

        verify(service, times(1)).getUserById(1L);
        verifyNoMoreInteractions(service);
    }


}