package com.example.Messages4.services;

import com.example.Messages4.dataAccess.MessageRepository;
import com.example.Messages4.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService{

    private MessageRepository messageRepository;

    @Override
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Iterable<Message> findByEmail(String email) {
        return messageRepository.findBySenderEmail(email);
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
