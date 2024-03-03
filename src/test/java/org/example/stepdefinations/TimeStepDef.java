package org.example.stepdefinations;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.Callable;

public class TimeStepDef {

    /**
     HRM_Time_NavigateTo_PunchIn
     @@Scenario: Positive navigation to Punch In page
     @@Given User clicks on Time link
     @@And User navigates to attendance tab and clicks on attendance dropdown
     @@And User clicks on PunchIn/Out
     @@Then User should be on Punch In page
     */
    @Given("User clicks on Time link")
    public void user_clicks_on_time_link() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/time/viewTimeModule')]")).click();
    }
    @Given("User navigates to attendance tab and clicks on attendance dropdown")
    public void user_navigates_to_attendance_tab_and_clicks_on_attendance_dropdown() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("(//span[@class='oxd-topbar-body-nav-tab-item'])[2]")).click();
    }
    @And("User clicks on PunchIn-Out")
    public void userClicksOnPunchInOut() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-dropdown-menu']/*)[2]")).click();
    }
    @Then("User should be on Punch In page")
    public void user_should_be_on_punch_in_page() {
        WebElement punchIn = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-card-container']/*"));
        System.out.println(punchIn.getText());
    }

    /**
     @HRM_Time_NavTabItems_Click
     @@Scenario: Positive clicks on all the navbar tab items
     @@Given User clicks on Time link
     @@And User clicks on all the navbar tab items
     @@Then User should be able to see nav tab items clicked
     */

    @Given("User clicks on all the navbar tab items")
    public void user_clicks_on_all_the_navbar_tab_items() throws InterruptedException {
        List<WebElement> navItems = CommonStepDefination.browserClass.getWebDriver().findElements(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])"));
        for (WebElement items: navItems) {
            items.click();
        }
    }
    @Then("User should be able to see nav tab items clicked")
    public void user_should_be_able_to_see_nav_tab_items_clicked() {
        List<WebElement> items = CommonStepDefination.browserClass.getWebDriver().findElements(By.xpath("(//*[@class='oxd-dropdown-menu'])/*"));
        for (WebElement elm: items) {
            System.out.println(elm.getText());
        }
    }

    /**
     HRM_Time_PunchIn_Test
     @@Scenario: Positive punch in test
     @@Given User clicks on Time link
     @@And User navigates to attendance tab and clicks on attendance dropdown
     @@And User clicks on PunchIn-Out
     @@And User checks the status of punch-in and if punched in, user will punch out before punching in
     @@Then User punch in
     */

    @Given("User checks the status of punch-in and if punched in, user will punch out before punching in")
    public void user_checks_the_status_of_punch_in_and_if_punched_in_user_will_punch_out_before_punching_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User should be able to punch in")
    public void userShouldBeAbleToPunchIn() {
    }
}
