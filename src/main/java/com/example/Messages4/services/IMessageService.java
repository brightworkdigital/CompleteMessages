package com.example.Messages4.services;

import com.example.Messages4.model.Message;

public interface IMessageService {
    Iterable<Message> getAllMessages();

    Iterable<Message> findByEmail(String email);
}
