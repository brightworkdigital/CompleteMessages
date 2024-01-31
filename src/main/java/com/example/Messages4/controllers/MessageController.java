package com.example.Messages4.controllers;

import com.example.Messages4.model.Message;
import com.example.Messages4.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MessageController {
    IMessageService messageService;

    @GetMapping("/messages")
    public Iterable<Message> getAllMessages()  {
        return messageService.getAllMessages();
    }

    @GetMapping("/getMessagesBySenderEmail/{email}")
    public Iterable<Message> getPersonByEmail(@PathVariable String email) {
        return messageService.findByEmail(email);
    }

    @Autowired
    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
