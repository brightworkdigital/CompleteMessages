package com.example.Messages4.controllerTests.basicUnitTests;

import com.example.Messages4.controllers.MessageController;
import com.example.Messages4.services.IMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MessageControllerUnitTests {
    MessageController uut;
    IMessageService messageService;

    @BeforeEach
    public void setUp()  {
        uut = new MessageController();
        messageService = mock(IMessageService.class);
        uut.setMessageService(messageService);
    }

    @Test
    public void testGetAllMessages()  {
        uut.getAllMessages();
        verify(messageService, times(1)).getAllMessages();
    }
}
