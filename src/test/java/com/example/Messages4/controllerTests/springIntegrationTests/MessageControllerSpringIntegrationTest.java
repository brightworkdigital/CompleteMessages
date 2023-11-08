package com.example.Messages4.controllerTests.springIntegrationTests;

import com.example.Messages4.controllers.MessageController;
import com.example.Messages4.services.IMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
public class MessageControllerSpringIntegrationTest {

    @MockBean
    IMessageService messageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testServiceLayerisCalled() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json"));

        verify(messageService, times(1)).getAllMessages();
    }
}
