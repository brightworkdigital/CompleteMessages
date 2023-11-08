package com.example.Messages4;

import com.example.Messages4.dataAccess.MessageRepository;
import com.example.Messages4.dataAccess.PersonRepository;
import com.example.Messages4.model.Message;
import com.example.Messages4.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Populator {

    private MessageRepository messageRepository;

    private PersonRepository personRepository;

    @Autowired
    public Populator(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    public Populator() {}

    public void run(String... args) {

        Person p = new Person("Fred", "fred@fredsemail.com");
        personRepository.save(p);

        Person p2 = new Person("Ethel", "ethel@ethelssemail.com");
        personRepository.save(p2);

        Person p3 = new Person("Lucy", "lucy@gmail.com");
        personRepository.save(p3);

        Person p4 = new Person("Ricky", "ricky@rocketmail.com");
        personRepository.save(p4);

        Message message1 = new Message(p, "Hello World");
        messageRepository.save(message1);

        Message message2 = new Message(p,"GLHF");
        messageRepository.save(message2);

        Message message3 = new Message(p3,"It's not raining");
        messageRepository.save(message3);
    }

}
