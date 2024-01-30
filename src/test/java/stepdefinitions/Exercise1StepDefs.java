package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Exercise1StepDefs {

    @Given("this is the given statement")
    public void this_is_the_given_statement() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Exercise 1 Given Step");
    }
    @When("this is the {string} statement")
    public void this_is_the_statement(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Exercise 1 When Step");
    }
    @Then("this is statement number {int}")
    public void this_is_statement_number(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Exercise 1 Then Step");
    }

}
