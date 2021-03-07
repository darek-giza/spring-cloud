package com.darek.giza.notificationservice.controller;

import com.darek.giza.notificationservice.service.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MailController.class)
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailServiceImpl mailService;

    @Test
    public void testSendMail() throws Exception {
        String email = "darek@gmail.com";
        mockMvc.perform(get("/sendMail/" + email)).andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testFailedSendMailWhenEmailIsEmpty() throws Exception {
        mockMvc.perform(get("/sendMail/")).andDo(print())
            .andExpect(status().is4xxClientError());
    }
}