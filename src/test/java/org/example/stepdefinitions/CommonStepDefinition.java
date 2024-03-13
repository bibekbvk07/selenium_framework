package org.example.stepdefinitions;

import io.cucumber.java.*;
import org.example.testngtests.GlobalFields;
import org.example.utilities.SingletonBrowserClass;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class CommonStepDefinition {
    public static SingletonBrowserClass browserClass;

    /**
     * Setup method to initialize the browser instance before all test methods.
     */
    @BeforeAll
    public static void setup(){
        browserClass = SingletonBrowserClass.getSingleBrowserInstance();
        JSONObject credential = new JSONObject();
        credential.put("username", "admin");
        credential.put("password", "password123");

        GlobalFields.ACCESS_TOKEN = given()
                .header("Content-Type", "application/json")
                .body(credential)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getString("token");
    }
    /**
     You can log information or capture screenshots after each test scenario to aid in debugging or reporting. For instance, if your test fails, you can take a screenshot of the browser window.
     */
    @After
    public static void after(){
        // code to take screenshot of failed and passed test cases

    }
    /**
     * Teardown method to quit the WebDriver after all test methods.
     */
    @AfterAll
    public static void teardown(){
        // Close the browser
        if (browserClass != null){
            browserClass.getWebDriver().quit();
        }
    }
}
