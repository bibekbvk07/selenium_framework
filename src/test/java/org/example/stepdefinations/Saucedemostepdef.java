package org.example.stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utilities.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Saucedemostepdef {
    WebDriver driver;

    @Given("User initialize the browser")
    public void user_initialize_the_browser() {
        CommonStepDefination.browserClass.getWebDriver().get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.id("user-name")).sendKeys("standard_user");
    }
    @When("User enters password {string}")
    public void user_enters_password(String password) {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.id("password")).sendKeys("secret_sauce");

    }

    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.id("login-button")).click();
        String title = CommonStepDefination.browserClass.getWebDriver().getTitle();
        System.out.println("The title of the website is: "+title);
        CommonStepDefination.browserClass.getWebDriver().quit();
    }
    @Given("User is in {string} homepage {string}")
    public void userIsInHomepage(String site, String url) {
        CommonStepDefination.browserClass.getWebDriver().get(url);
    }


    @Given("HR searching for {string} for post of {string} {string}")
    public void hr_searching_for_for_post_of(String job_title, String total, String vacancy) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(job_title);
        System.out.println(total);
        System.out.println(vacancy);
        if(vacancy.equals("Architect")){
            Assert.fail("Test failure");
        }
    }

    @When("User enters correct credentials")
    public void user_enters_correct_credentials(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        for(List<String> columns: data) {
            CommonStepDefination.browserClass.getWebDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(columns.get(0));
            CommonStepDefination.browserClass.getWebDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(columns.get(1));
            System.out.println(columns.get(0));
            System.out.println(columns.get(1));
        }
    }

    @Then("find recruitment")
    public void findRecruitment() {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//span[text()='Recruitment']")).click();
    }
    @When("User enters username as {string} and password as {string}")
    public void userEntersUsernameAsAndPasswordAs(String username, String password) {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);
        CommonStepDefination.browserClass.getWebDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(password);
    }

    @Then("User should be able to see error message {string}")
    public void userShouldBeAbleToSeeErrorMessage(String errormessage) {
        CommonStepDefination.browserClass.getWebDriver().findElement(By.cssSelector("input[id='login-button']")).click();
        String errorMsg = CommonStepDefination.browserClass.getWebDriver().findElement(By.xpath("//div[contains(@class,'error-message-container')]/h3[contains(text(),'"+errormessage+"')]")).getText();
        if (errorMsg.contains(errormessage)){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Text doesn't contains locked out user");
        }
    }
}