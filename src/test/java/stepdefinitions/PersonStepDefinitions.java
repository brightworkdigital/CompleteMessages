package stepdefinitions;

import com.example.Messages4.model.Message;
import com.example.Messages4.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PersonStepDefinitions {

    Person[] persons;
    Person testPerson;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Given("I want to add a person with name {string} and email {string}")
    public void iWantToAddAPersonWithNameAndEmail(String name, String email) {
        testPerson = new Person(name, email);
    }

    @When("I enter the information")
    public void iEnterTheInformation() throws Exception {
        String json = mapper.writeValueAsString(testPerson);

        mockMvc.perform(MockMvcRequestBuilders.post("/addPerson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Then("that person is added to my Person database table")
    public void thatPersonIsAddedToMyPersonDatabaseTable() throws Exception {
        boolean isOnList = false;

        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        persons = mapper.readValue(contentAsString, Person[].class);

        for(Person p : persons) {
            if(p.getName().equals(testPerson.getName()) && p.getEmail().equals(testPerson.getEmail())) {
                isOnList = true;
                break;
            }
        }
        assertTrue(isOnList);
    }
}
