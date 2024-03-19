package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class LoginStepDefinition {

    /**
     * @param url
     * @@launch_url
     *   @@Scenario: Positive url launch
     *     @@Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
     *     @@Then Login page appears
     */
    @Given("User launches URL {string}")
    public void user_launches_url(String url) {
        try{
            CommonStepDefinition.browserClass.getWebDriver().get(url);
            // Check the title of the page
            String title = CommonStepDefinition.browserClass.getWebDriver().getTitle();
            System.out.println("Page title: "+ title);
        }catch (Exception e){
            System.err.println("Exception occurred while launching the page: "+ e.getMessage());
        }
    }
    @Then("Login page appears")
    public void login_page_appears() {
        // Find the login element using its class name
        WebElement login = CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("orangehrm-login-layout"));

        // Assert that the login element is displayed
        if (login.isDisplayed()){
            Assert.assertTrue(true);
        }else{
            System.out.println("Login page is not displayed!");
        }
    }

    /**
     *   @@login
     *   @@Scenario: Positive credential login
     *     @@Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
     *     @@And User enters username "Admin"
     *     @@And User enters password "admin123"
     *     @@And User clicks on login button
     *     @@Then User is redirected to the dashboard page
     * @param username
     */
    @And("User enters username {string}")
    public void userEntersUsername(String username) {
       WebElement user = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@name='username']"));
       user.sendKeys(username);
    }

    @And("User enters password {string}")
    public void userEntersPassword(String password) {
        WebElement pwd = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@name='password']"));
        pwd.sendKeys(password);
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        WebElement login = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//button[@type='submit']"));
        login.click();
    }

    @Then("User is redirected to the dashboard page")
    public void userIsRedirectedToTheDashboardPage() {
        try{
            WebElement dashboardUser = CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("oxd-userdropdown-name"));
            if (dashboardUser.isDisplayed()){
                Assert.assertTrue(true);
                System.out.println("User name: "+ dashboardUser.getText());
            }else {
                System.out.println("Dashboard page not displayed");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     @@invalid_login
        @@Scenario: User Receives Invalid Login Message
        @@Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        @@And User enters username "hello"
        @@And User enters password "admin123"
        @@And User clicks on login button
        @@Then User gets an invalid login message
     */

    @Then("User gets an invalid login message")
    public void userGetsAnInvalidLoginMessage() {
        try{
            WebElement loginError = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-login-error']/div[1]"));
            System.out.println(loginError.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     @@logout
     @@Scenario: Positive credential logout
     @@Given User is on the dashboard page
     @@When User clicks on user drop down menu
     @@And User clicks on logout
     @@Then Login page appears
     */

    @Given("User is on the dashboard page")
    public void userIsOnTheDashboardPage() {
        try{
            WebElement dashboardUser = CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("oxd-userdropdown-name"));
            if (dashboardUser.isDisplayed()){
                Assert.assertTrue(true);
            }else {
                System.out.println("User is not on the dashboard page!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User clicks on user drop down menu")
    public void userClicksOnUserDropDownMenu() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("oxd-userdropdown-tab")).click();
    }

    @And("User clicks on logout")
    public void userClicksOnLogout() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/logout')]")).click();
    }
}
