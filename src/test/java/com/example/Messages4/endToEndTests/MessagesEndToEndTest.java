package com.example.Messages4.endToEndTests;

import com.example.Messages4.model.Message;
import com.example.Messages4.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MessagesEndToEndTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();


    @Test
    @Sql("classpath:test-data.sql")
    public void testGettingAllMessages() throws Exception {

        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message[] messages = mapper.readValue(contentAsString, Message[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(3, messages.length),
                () -> assertEquals("First test message", messages[0].getContent()),
                () -> assertEquals("Second test message", messages[1].getContent()),
                () -> assertEquals("Will", messages[0].getSender().getName())
                );
    }

    @Test
    @Sql( "classpath:test-data.sql")
    public void testGettingAllPersons() throws Exception {

        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Person[] persons = mapper.readValue(contentAsString, Person[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(3, persons.length),
                () -> assertEquals("Will", persons[0].getName()),
               () -> assertEquals("Tori", persons[1].getName()),
                () -> assertEquals("T", persons[2].getName())
        );
    }

    @Test
    @Sql( "classpath:test-data.sql")
    public void testGettingPersonByName() throws Exception {
        String name = "T";
        String expectedEmail= "more@email.com";

        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/persons/" +name)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Person person = mapper.readValue(contentAsString, Person.class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedEmail, person.getEmail())

        );
    }

    @ParameterizedTest
    @CsvSource({"Will, first@email.com", "Tori, another@email.com", "T, more@email.com"})
    @Sql( "classpath:test-data.sql")
    public void testGettingPeopleByName(String name, String expectedEmail) throws Exception {

        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/persons/" +name)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Person person = mapper.readValue(contentAsString, Person.class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedEmail, person.getEmail())

        );
    }
}
