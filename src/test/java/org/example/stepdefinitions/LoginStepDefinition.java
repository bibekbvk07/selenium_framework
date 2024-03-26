package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.example.locators.LoginLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginStepDefinition {

    @Given("User launches URL {string} and validates title {string}")
    public void userLaunchesURLAndValidates(String url, String validate) {
        CommonStepDefinition.browserClass.getWebDriver().get(url);
        // Check the title of the page
        String title = CommonStepDefinition.browserClass.getWebDriver().getTitle();
        Assert.assertEquals(title, validate, "Title of the page is different!");
    }

    @Then("Login page appears and validates header {string}")
    public void login_page_appears(String validator) {
        // Find the login element using its class name
        WebElement login = CommonStepDefinition.browserClass.getWebDriver().findElement(LoginLocator.getLoginLocator());
        // Assert that the login element is displayed
        Assert.assertEquals(login.getText(), validator, "Login page not found!");
    }

    @When("User enters {string} {string}")
    public void userEnters(String element, String value) {
        WebElement tagName = CommonStepDefinition.browserClass.getWebDriver().findElement(By.name(element));
        tagName.sendKeys(value);
        Assert.assertEquals(tagName.getAttribute("value"), value);
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        WebElement login = CommonStepDefinition.browserClass.getWebDriver().findElement(LoginLocator.getLoginButtonLocator());
        Assert.assertTrue(login.isDisplayed(), "Login button not found!");
        login.click();
    }

    @Then("User gets an invalid message {string}")
    public void userGetsAnInvalidMessage(String errorMessage) {
        WebElement loginError = CommonStepDefinition.browserClass.getWebDriver().findElement(LoginLocator.getInvalidLoginLocator());
        Assert.assertEquals(loginError.getText(), errorMessage);
    }

    @Then("User is redirected to the dashboard page and validates {string}")
    public void userIsRedirectedToTheDashboardPageAndValidates(String validator) {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(LoginLocator.getValidLoginValidator());
        Assert.assertEquals(element.getText(), validator, "User is not redirected to dashboard page.");
    }
}
