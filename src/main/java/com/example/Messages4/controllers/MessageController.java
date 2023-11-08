package com.example.Messages4.controllers;

import com.example.Messages4.model.Message;
import com.example.Messages4.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class MessageController {
    IMessageService messageService;

    @GetMapping("/messages")
    public Iterable<Message> getAllMessages()  {
        return messageService.getAllMessages();
    }

    @Autowired
    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
