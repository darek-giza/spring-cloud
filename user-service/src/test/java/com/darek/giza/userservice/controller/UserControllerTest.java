package com.darek.giza.userservice.controller;


import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.model.UserPartial;
import com.darek.giza.userservice.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static com.darek.giza.userservice.helper.UserTestUtils.getUser;
import static com.darek.giza.userservice.helper.UserTestUtils.getUsers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    public static final String URL_USER = "/user";
    public static final String URL_USER_BY_ID = "/user/{id}";
    public static final String URL_MAIL = "http://172.19.0.4:8086/sendMail/";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MESSAGE = "User created successful !!!";
    public static final String EMAIL = "darek_giza@op.pl";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAll() throws Exception {
        List<User> users = getUsers();
        when(userService.getAll()).thenReturn(users);
        mockMvc.perform(get(URL_USER))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is("11id")))
            .andExpect(jsonPath("$[1].id", is("22id")))
            .andExpect(jsonPath("$[0].email", is("darek_giza@op.pl")))
            .andExpect(jsonPath("$[1].email", is("marek_giza@op.pl")))
            .andExpect(jsonPath("$[0].password", is("pass")))
            .andExpect(jsonPath("$[1].password", is("pass")))
            .andExpect(jsonPath("$[0].firstName", is("Darek")))
            .andExpect(jsonPath("$[1].firstName", is("Darek")))
            .andExpect(jsonPath("$[0].lastName", is("Giza")))
            .andExpect(jsonPath("$[1].lastName", is("Giza")))
            .andReturn();
        verify(userService, times(1)).getAll();

    }

    @Test
    public void testGetById() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        when(userService.getById(id)).thenReturn(user);

        mockMvc.perform(get(URL_USER_BY_ID, id))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(id)))
            .andExpect(jsonPath("$.email", is("darek_giza@op.pl")))
            .andExpect(jsonPath("$.password", is("pass")))
            .andExpect(jsonPath("$.firstName", is("Darek")))
            .andExpect(jsonPath("$.lastName", is("Giza")))
            .andReturn();
        verify(userService, times(1)).getById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        UserPartial userPartial = UserPartial.builder().firstName(FIRST_NAME).firstName(LAST_NAME).build();
        when(userService.updateById(id, userPartial)).thenReturn(user);
        String req = objectMapper.writeValueAsString(userPartial);

        mockMvc.perform(put(URL_USER_BY_ID, id)
            .content(req)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        verify(userService, times(1)).updateById(id, userPartial);
    }

    @Test
    public void testCreate() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = getUser(id, EMAIL);
        String req = objectMapper.writeValueAsString(user);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MESSAGE, HttpStatus.OK);
        when(userService.create(user)).thenReturn(user);
        when(restTemplate.exchange(URL_MAIL + EMAIL, HttpMethod.GET, null, String.class)).thenReturn(responseEntity);

        mockMvc.perform(post(URL_USER)
            .content(req)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();
        verify(userService, times(1)).create(user);
    }
}
