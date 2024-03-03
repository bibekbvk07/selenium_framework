package org.example.stepdefinations;

import io.cucumber.java.en.*;
import org.example.globalfiles.GlobalFile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OrangeStepDef {
    /**
     * @param url
     *
     @OrangeHRMTest

     @@Scenario: Positive credential validations
     @@Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
     @@When Users enters username "Admin"
     @@And Users enters password "admin123"
     @@And User clicks on Login
     @@Then User should be able to navigate the Homepage
     */

    @Given("User launches URL {string}")
    public void user_launches_url(String url) {
        CommonStepDefination.browserClass.getWebDriver().get(url);
    }
    @When("Users enters username {string}")
    public void users_enters_username(String username) {
       CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(username);
    }
    @When("Users enters password {string}")
    public void users_enters_password(String password) {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }
    @When("User clicks on Login")
    public void user_clicks_on_login() {
        WebDriverWait wait = new WebDriverWait(CommonStepDefination.browserClass.getWebDriver(), Duration.ofSeconds(10));
        WebElement myInfoLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
        myInfoLink.click();
    }
    @Then("User should be able navigate Homepage")
    public void user_should_be_able_navigate_homepage() {
        boolean display = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).isDisplayed();
        if (display){
            Assert.assertTrue(true);
            System.out.println("User logged in name: "+
                    CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText());
        }else {
            Assert.fail("Login Failed!");
        }
    }
    /**
     @Invalid_Login_HRM1
     @@Scenario: Negative credential login
     @@Given Users enters invalid username
     @@When User enters invalid password
     @@And User clicks on Login
     @@Then User should be able to see invalid login message
     */

    @Then("User should be able to see invalid login message")
    public void user_should_be_able_to_see_invalid_login_message() {
        try{
            WebElement invalidMsg = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-login-error']/div[1]"));
            WebDriverWait wait = new WebDriverWait(CommonStepDefination.browserClass.getWebDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(invalidMsg));

            if (invalidMsg.isDisplayed()){
                Assert.assertTrue(true);
                System.out.println(invalidMsg.getText());
            }else{
                Assert.fail("Element not displayed!");
            }
        }catch (NoSuchElementException e){
            System.out.println("Successful Login: "+ e.getMessage());
        }
    }

    /**
     @MyInfoTest

     @@Scenario: Positive click on My Info link
     @@When User clicks on My Info link
     @@Then User should see Personal Detail Page
     */

    @When("User clicks on My Info link")
    public void user_clicks_on_my_info_link() {
        WebElement element = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/viewMyDetails')]"));
        element.click();
    }
    @Then("User should see Personal Detail Page")
    public void user_should_see_personal_detail_page() {
        WebElement personalPage = CommonStepDefination.browserClass.getWebDriver().findElement(By.className("employee-image"));
        if (personalPage.isDisplayed()){
            Assert.assertTrue(true);
        }else{
            Assert.fail("Page not available");
        }
    }

    /**
     @OrangeHRMTest1
     @@Scenario: Positive scroll on the Homepage
     @@Given User is logged in and on the Homepage
     @@When User scrolls down the Homepage
     @@Then the content on the Homepage should be visible after scrolling
     */

    @When("User scrolls down the Homepage")
    public void user_scrolls_down_the_homepage() {
        try{
            WebElement scrollElement = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//p[text()='OrangeHRM OS 5.6']"));
            ((JavascriptExecutor) CommonStepDefination.browserClass.getWebDriver()).executeScript("arguments[0].scrollIntoView(true)", scrollElement);
            Thread.sleep(2000);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @Then("the content on the Homepage should be visible after scrolling")
    public void the_content_on_the_homepage_should_be_visible_after_scrolling() {
        WebElement element = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//p[text()='OrangeHRM OS 5.6']"));
        if (element.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println("Content visible after scrolling: "+ element.getText());
        }else {
            Assert.fail("Content not visible after scrolling");
        }
    }

    /**
     @OrangeHRMTest2

     @@Scenario: User opens 'My Info' in a new tab and switches to the new tab.
     @@Given User is logged in and on the Homepage
     @@When User navigates to My Info section and opens link in new tab
     @@And User switches the window to new tab
     @@Then User should see Personal Details
     */
    @When("User navigates to My Info section and opens link in new tab")
    public void user_navigates_to_my_info_section_and_opens_link_in_new_tab() {
       Actions actions = new Actions(CommonStepDefination.browserClass.getWebDriver());
       WebElement myInfo = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/viewMyDetails')]"));
       myInfo.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));

        GlobalFile.globalWindowHandle = CommonStepDefination.browserClass.getWebDriver().getWindowHandle();
        System.out.println("ParentWindowID: "+ GlobalFile.globalWindowHandle);
    }
    @When("User switches the window to new tab")
    public void user_switches_the_window_to_new_tab() {
        List<String> windowHandles = new ArrayList<>(CommonStepDefination.browserClass.getWebDriver().getWindowHandles());
        for (String handle : windowHandles) {
            if (!handle.equals(GlobalFile.globalWindowHandle)){
                CommonStepDefination.browserClass.getWebDriver().switchTo().window(handle);
                System.out.println("ChildWindoID: "+ handle);
                break;
            }
        }
    }
    @Then("User should be in Personal Details tab")
    public void userShouldBeInPersonalDetailsTab() {
        WebElement personalPage = CommonStepDefination.browserClass.getWebDriver().findElement(By.className("employee-image"));
        if (personalPage.isDisplayed()){
            Assert.assertTrue(true);
        }else{
            Assert.fail("Page not available");
        }
    }

    /**
     @@OrangeHRMTest3

     @@Scenario: User updates personal details
     @@When User navigates to My Info section and opens link in new tab
     @@And User switches the window to new tab
     @@Then User should be in Personal Details tab
     @@When User clicks on firstName input field
     @@And User clears the firstNameinput field
     @@And User updates the firstName input field
     @@When User clicks on lastName input field
     @@And User clears the lastName input field
     @@And User updates the lastName input field
     @@And User clicks on Save and
     @@And User closes the current tab
     @@Then User should be in the main tab
     */

    @When("User clicks on firstName input field")
    public void user_clicks_on_first_name_input_field() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.name("firstName")).click();
    }
    @When("User clears the firstNameinput field")
    public void user_clears_the_first_nameinput_field() {
        WebElement fname = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//input[@placeholder='First Name']"));
        fname.clear();
    }
    @When("User updates the firstName input field")
    public void user_updates_the_first_name_input_field() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.name("firstName")).sendKeys("Chris");
    }
    @When("User clicks on lastName input field")
    public void user_clicks_on_last_name_input_field() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.name("lastName")).click();
    }
    @When("User clears the lastName input field")
    public void user_clears_the_last_name_input_field() {
        WebElement lname = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//input[@placeholder='Last Name']"));
        lname.clear();
    }
    @When("User updates the lastName input field")
    public void user_updates_the_last_name_input_field() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.name("lastName")).sendKeys("Morgan");
    }
    @When("User clicks on Save and")
    public void user_clicks_on_save_and() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[1]")).click();
    }
    @When("User closes the current tab")
    public void user_closes_the_current_tab() {
        CommonStepDefination.browserClass.getWebDriver().close();
        CommonStepDefination.browserClass.getWebDriver().switchTo().window(GlobalFile.globalWindowHandle);
    }
    @Then("User should be in the main tab")
    public void user_should_be_in_the_main_tab() {
        String windowHandle = CommonStepDefination.browserClass.getWebDriver().getWindowHandle();

        if (GlobalFile.globalWindowHandle.equals(windowHandle)){
            Assert.assertTrue(true);
            System.out.println("Global windowID: "+ GlobalFile.globalWindowHandle);
            System.out.println("Current windowID: "+ windowHandle);
        }else{
            Assert.fail("Wrong tab!");
        }
    }
}
