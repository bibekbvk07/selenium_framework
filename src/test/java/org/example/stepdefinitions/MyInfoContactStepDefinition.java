package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class MyInfoContactStepDefinition {
    @Given("User clicks on contact details")
    public void user_clicks_on_contact_details() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/contactDetails/')]")).click();
    }
}
