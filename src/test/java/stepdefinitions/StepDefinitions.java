package stepdefinitions;

import com.example.Messages4.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

public class StepDefinitions {
    Message[] messages;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Given("I request all messages")
    public void iRequestAllMessages() throws Exception {
        messages = performMockGetAndReturnMessages("/messages");
    }

    @Then("the total number of messages I receive is {int}")
    public void theTotalNumberOfMessagesIReceiveIs(int expectedTotalMessages) {
        assertEquals(expectedTotalMessages, messages.length);
    }

    @Given("I request messages by the senders email {string}")
    public void i_request_messages_by(String email) throws Exception {
        String url = "/getMessagesBySenderEmail/" + email;
        messages = performMockGetAndReturnMessages(url);
    }

    private Message[] performMockGetAndReturnMessages(String url) throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders.get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        return mapper.readValue(contentAsString, Message[].class);
    }
}
