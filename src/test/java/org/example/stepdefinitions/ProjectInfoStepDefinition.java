package org.example.stepdefinitions;

import QA_Practice.Alerts;
import io.cucumber.java.en.*;
import org.example.globalfiles.GlobalFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProjectInfoStepDefinition {
    @Given("User hovers and clicks on projectInfo")
    public void user_hovers_and_clicks_on_project_info() {
        Actions actions = new Actions(CommonStepDefinition.browserClass.getWebDriver());
        WebElement projectInfo = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[4]"));
        actions.moveToElement(projectInfo).build().perform();
        projectInfo.click();
    }
    @When("User clicks on my customers")
    public void user_clicks_on_my_customers() {
        WebElement customers = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-dropdown-menu']/*)[1]"));
        customers.click();
    }
    @Then("Customers page appears")
    public void customers_page_appears() {
        WebElement customersPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-header-container']/h6"));
        if (customersPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println("Customers page header: "+ customersPage.getText());
        }else{
            Assert.fail("Customers page not found!");
        }
    }
    @When("User clicks on add button")
    public void userClicksOnAddButton() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-header-container']/div/button")).click();
    }

    @And("User enters name {string}")
    public void userEntersName(String name) {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-form-row']/div/div/input")).sendKeys(name);
    }

    @And("User type description {string}")
    public void userTypeDescription(String description) {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-form-row']/div/div/textarea")).sendKeys(description);
    }

    @And("User clicks on save button")
    public void userClicksOnSaveButton() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']")).click();
    }

    @Then("User should be able to add customers")
    public void userShouldBeAbleToAddCustomers() {
         GlobalFile.nameList = CommonStepDefinition.browserClass.getWebDriver().findElements(By.xpath("//div[@class='oxd-table-body']/div/div/div[2]"));
        for (WebElement element: GlobalFile.nameList) {
            if (element.getText().equals("Dominium")){
                Assert.assertTrue(true);
            }else {
                System.out.println("Customer was not added!");
            }
        }
    }
    @Then("User should see error message")
    public void userShouldSeeErrorMessage() {
        WebElement alreadyExist = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(4));
        if (wait.until(ExpectedConditions.visibilityOf(alreadyExist)).isDisplayed()){
            Assert.assertEquals(alreadyExist.getText(), "Already exists", "Sorry Name already exist!");
        }else{
            Assert.fail("Name added!");
        }
    }

    @When("User selects the checkbox name {string}")
    public void userSelectsTheCheckboxName(String name) {
        for (WebElement element: GlobalFile.nameList) {
            if (element.getText().equals(name)){
                CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-card']/div/div[1]")).click();
                GlobalFile.index = GlobalFile.nameList.indexOf(element);
            }
        }
    }
    @And("User clicks delete button")
    public void userClicksDeleteButton() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-body']/div["+GlobalFile.index+"]/div/div[4]/div/button[1]")).click();
    }

    @And("User gets alert pop up text")
    public void userGetsAlertPopUpText() {
        System.out.println(CommonStepDefinition.browserClass.getWebDriver().switchTo().alert().getText());
    }

    @And("User dismiss the alert pop up")
    public void userDismissTheAlertPopUp() {
        CommonStepDefinition.browserClass.getWebDriver().switchTo().alert().dismiss();
    }

    @Then("Alert pop should be dismissed")
    public void alertPopShouldBeDismissed() {
        WebElement customersPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-header-container']/h6"));
        if (customersPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println("Customers page header: "+ customersPage.getText());
        }else{
            Assert.fail("Customers page not found!");
        }
    }
}
