package org.example.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.locators.MyInfoLocator;
import org.example.locators.SidePanelLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SidePanelStepDefinition {
    private static List<String> tabs;
    @Then("User clicks {string} in the side panel navigation bar and validates header {string}")
    public void userClicksOnAndValidates(String locator, String validator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(15));
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(SidePanelLocator.getSidePanelLocators(locator));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        //Assert Validation
       Thread.sleep(2000);
        WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(SidePanelLocator.getSidePanelHeaderValidator());
        Assert.assertEquals(elementValidation.getText(), validator, "Element was not a match!");
    }

    @When("User opens {string} link on new tab and validates the url {string}")
    public void userOpensLinkOnNewTabAndValidatesTheUrl(String locator, String urlValidation) throws InterruptedException {
        String currentWindowHandle = CommonStepDefinition.browserClass.getWebDriver().getWindowHandle();
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(SidePanelLocator.getSidePanelLocators(locator));
        String keyBoardAction = Keys.chord(Keys.COMMAND, Keys.ENTER);
        element.sendKeys(keyBoardAction);

        //Stores the list of the window handles
        tabs = new ArrayList<>(CommonStepDefinition.browserClass.getWebDriver().getWindowHandles());

        // tabs.get(0) contains the currentWindowHandle
        CommonStepDefinition.browserClass.getWebDriver().switchTo().window(tabs.get(1));
        String urlOfNewTab = CommonStepDefinition.browserClass.getWebDriver().getCurrentUrl();
        String switchedWindowHandle = CommonStepDefinition.browserClass.getWebDriver().getWindowHandle();
        Assert.assertEquals(urlOfNewTab, urlValidation, "Switch Tab failed!");
        Assert.assertNotEquals(currentWindowHandle, switchedWindowHandle);
    }

    @Then("User clicks on {string} and validates header {string}")
    public void userClicksOnAndValidatesHeader(String locator, String validator) {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(SidePanelLocator.getSidePanelLocators(locator));
        element.click();
        //Assert Validation
        WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(SidePanelLocator.getSidePanelHeaderValidator());
        Assert.assertEquals(elementValidation.getText(), validator, "Element was not a match!");
    }
}
