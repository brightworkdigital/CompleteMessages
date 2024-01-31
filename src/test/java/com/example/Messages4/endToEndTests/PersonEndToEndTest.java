package com.example.Messages4.endToEndTests;

import com.example.Messages4.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonEndToEndTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();
    JdbcTemplate jdbcTemplate;

    @BeforeAll
    public void setUp() throws IOException {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // Load SQL Script and execute it
        String sqlScript = new String(Files.readAllBytes(Paths.get("src/test/resources/test-data.sql")));
        jdbcTemplate.execute(sqlScript);
    }


    @Test
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
