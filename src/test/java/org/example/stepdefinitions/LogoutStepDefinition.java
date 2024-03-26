package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.example.locators.LoginLocator;
import org.example.locators.LogoutLocator;
import org.example.locators.MyInfoLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutStepDefinition {
    @When("User clicks on user drop down menu and validates header {string}")
    public void userClicksOnUserDropDownMenu(String validator) {
        WebElement userDropDown = CommonStepDefinition.browserClass.getWebDriver().findElement(LogoutLocator.getUserDropdownLocator());
        userDropDown.click();
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(10));
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(LogoutLocator.getLogoutLocator());
        wait.until(ExpectedConditions.visibilityOf(validation));
        Assert.assertEquals(validation.getText(), validator, "Drop down option menu did not appear.");
    }

    @And("User clicks on logout and validates {string} page")
    public void userClicksOnLogoutAndValidatesTheHeader(String validator) {
        CommonStepDefinition.browserClass.getWebDriver().findElement(LogoutLocator.getLogoutLocator()).click();
        WebElement logoutValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(LoginLocator.getLoginLocator());
        Assert.assertEquals(logoutValidation.getText(), validator, "Logout Unsuccessful");
    }
}
