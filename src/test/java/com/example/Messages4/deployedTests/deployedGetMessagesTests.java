package com.example.Messages4.deployedTests;

import com.example.Messages4.model.Message;
import com.example.Messages4.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("TestFailedLine")
public class deployedGetMessagesTests {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllMessagesTest() throws IOException {

        HttpUriRequest request = new HttpGet("http://localhost:8080/messages");

        HttpResponse response = (HttpResponse) HttpClientBuilder.create().build().execute(request);

        Message[] messages = mapper.readValue(response.getEntity().getContent(), Message[].class);

        assertAll("Testing the deployed app",
                () -> assertEquals(4, messages.length),
                () -> assertEquals("Hello World", messages[0].getContent()),
                () -> assertEquals("GLHF", messages[1].getContent()),
                () -> assertEquals("It's not raining", messages[2].getContent()),
                () -> assertEquals("another message", messages[3].getContent())
        );
    }

    HttpUriRequest getRequest;
    HttpResponse response;
    HttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void testAddingPerson() throws IOException {
        int originalLength = getOriginalLength();

        String name = "Oscar";
        String email = "email@address.com";
        Person person = new Person(name, email);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(person);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost("http://localhost:8080/addPerson");
        request.setEntity(entity);
        httpClient.execute(request);

        response = (HttpResponse) HttpClientBuilder.create().build().execute(getRequest);
        Person[] persons2 = mapper.readValue(response.getEntity().getContent(), Person[].class);

        assertAll("adding a Person worked",
                () ->assertEquals(originalLength + 1, persons2.length),
                () -> assertTrue(checkIfOnList(name, email, persons2))
        );
    }

    private int getOriginalLength() throws IOException {
        getRequest = new HttpGet("http://localhost:8080/persons");
        response = (HttpResponse) HttpClientBuilder.create().build().execute(getRequest);
        Person[] persons = mapper.readValue(response.getEntity().getContent(), Person[].class);
        return persons.length;
    }

    private boolean checkIfOnList(String name, String email, Person[] persons) {
        boolean isOnList = false;
        for(Person p : persons) {
            if (p.getName().equals(name) && p.getEmail().equals(email)) {
                isOnList = true;
            }
        }return isOnList;
    }
}
