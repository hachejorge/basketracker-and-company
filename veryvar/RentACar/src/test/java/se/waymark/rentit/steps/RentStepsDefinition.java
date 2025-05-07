package se.waymark.rentit.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.waymark.rentit.RentACarSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RentStepsDefinition {
    private RentACarSupport rentACarSupport = new RentACarSupport();
    @Given("there are {int} cars available for rental")
    public void there_are_cars_available_for_rental(Integer availableCars) {
        rentACarSupport.createCars(availableCars);
    }

    @When("I rent one")
    public void i_rent_one() {
        rentACarSupport.rentACar();
    }

    @When("I return one")
    public void i_return_one() {
        rentACarSupport.returnACar();
    }

    @Then("there will only be {int} cars available for rental")
    public void there_will_only_be_cars_available_for_rental(Integer expectedAvailableCars) {
        int actualAvailableCars = rentACarSupport.getAvailableNumberOfCars();
        assertThat(actualAvailableCars, is(expectedAvailableCars));
    }

}
