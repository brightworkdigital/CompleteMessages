package stepdefinitions;

import com.example.Messages4.model.Message;
import com.example.Messages4.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
public class StepDefinitions {
    Message[] messages;

    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Given("I request all messages")
    public void iRequestAllMessages() throws Exception {
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        messages = mapper.readValue(contentAsString, Message[].class);
    }

    @Then("the total number of messages I receive is {int}")
    public void theTotalNumberOfMessagesIReceiveIs(int expectedTotalMessages) {
    assertEquals(expectedTotalMessages, messages.length);
    }

    @Given("I request messages by the senders email {string}")
    public void i_request_messages_by(String email) throws Exception {
        String url = "/getMessagesBySenderEmail/" + email;
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        messages = mapper.readValue(contentAsString, Message[].class);
    }
}
