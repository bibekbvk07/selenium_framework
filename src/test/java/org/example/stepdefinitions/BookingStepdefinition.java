package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.globalfiles.GlobalFile;
import org.example.pojo.Booking;
import org.example.pojo.BookingId;
import org.example.testngtests.GlobalFields;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class BookingStepdefinition {

    private Response response;
    private List<BookingId> bookingIdList;
    private int bookingId;

    @Given("the base URL is {string}")
    public void the_base_url_is(String baseUrl) {
        // Set the base URL for the API requests
        RestAssured.baseURI = baseUrl;
    }
    @When("a GET request is made to {string}")
    public void a_get_request_is_made_to(String endpoint) {
        // Make a GET request to retrieve all bookings
        response = given().when().get(endpoint);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int code) {
        // Verify the response status code
        response.then().statusCode(code);
    }
    @Then("the response should contain a list of bookings")
    public void the_response_should_contain_a_list_of_bookings() {
        // Verify that the response contains a list of bookings
        bookingIdList = response.jsonPath().getList("", BookingId.class);
        bookingId = bookingIdList.get(0).getBookingid();
        Assert.assertTrue(bookingIdList.size()> 5);
    }
    @Then("the booking ID is stored in the global variable")
    public void theBookingIDIsStoredInTheGlobalVariable() {
        GlobalFields.BOOKING_ID = bookingId;
    }

    @When("a GET request is made to {string} with stored booking Id")
    public void aGETRequestIsMadeToWithStoredBookingId(String endpoint) {
        response = given()
                .pathParam("bookingid", GlobalFields.BOOKING_ID)
                .when().get(endpoint);
    }

    @And("the response should contain the details of the booking with the stored ID")
    public void theResponseShouldContainTheDetailsOfTheBookingWithTheStoredID() {
        Booking booking = response.then().extract().body().as(Booking.class);
        System.out.println(booking.toString());
    }


}
