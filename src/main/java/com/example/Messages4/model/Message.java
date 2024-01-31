package com.example.Messages4.model;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    long id;

    String content;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne
    @JoinColumn(name = "person_id")
    Person sender;

    public Message()  {}

    public Message(Person sender, String content) {
        this.content = content;
        this.sender = sender;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }
}
