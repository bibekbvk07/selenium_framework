package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.example.globalfiles.GlobalFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeStepDefinition {

    private boolean isChecked;

    @And("User clicks on time link")
    public void user_clicks_on_time_link() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/time/viewTimeModule')]")).click();
    }
    @Given("User hovers and clicks on attendance")
    public void user_hovers_and_clicks_on_attendance() {
        Actions actions = new Actions(CommonStepDefinition.browserClass.getWebDriver());
        WebElement attendance = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[2]"));
        actions.moveToElement(attendance).build().perform();
        attendance.click();
    }

    /**
     @@time_attendance_myRecords
     @Scenario: Positive navigation to the My Record page
     @When User clicks on my records
     @Then My record page appears
     */

    @When("User clicks on my records")
    public void userClicksOnMyRecords() {
        WebElement myRecords = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[1]"));
        myRecords.click();
    }

    @Then("My record page appears")
    public void myRecordPageAppears() {
        WebElement myRecordsPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-filter-header-title']/h5"));
        if (myRecordsPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println("Record page header: "+ myRecordsPage.getText());
        }else{
            Assert.fail("My Records page was not found!");
        }
    }

    /**
     @@time_attendance_punchInOut
     @Scenario: Positive navigation to the punch in/out page
     @When User clicks on punch in-out
     @Then Punch in-out page appears
     */
    @When("User clicks on punch in-out")
    public void user_clicks_on_punch_in_out() {
        WebElement punchInOut = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[2]"));
        punchInOut.click();
    }
    @Then("Punch in-out page appears")
    public void punch_in_out_page_appears() {
        WebElement punchInPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
        if (punchInPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println(punchInPage.getText());
        }else{
            Assert.fail("Punch In/Out page was not found!");
        }
    }

    /**
     @@time_attendance_employeeRecords
        @Scenario: Positive navigation to the Employee Records page
        @When User clicks on employee records
        @Then Employee records page appears
     */

    @When("User clicks on employee records")
    public void userClicksOnEmployeeRecords() {
        WebElement employeeRecords = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[3]"));
        employeeRecords.click();
    }

    @Then("Employee records page appears")
    public void employeeRecordsPageAppears() {
        WebElement employeeRecordsPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-filter-header-title']/h5"));
        if (employeeRecordsPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println(employeeRecordsPage.getText());
        }else{
            Assert.fail("Employee Records page was not found!");
        }
    }

    @Then("Employee should be able to see the list")
    public void employeeShouldBeAbleToSeeTheList() {
        Map<String, Double> employeeAttendanceRecords = new HashMap<>();
        WebElement stringCount = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//span[@class='oxd-text oxd-text--span']"));
        String extractDigit = stringCount.getText();

        // Extracting digit with the parenthesis
        int startIndex = extractDigit.indexOf('(');
        int endIndex = extractDigit.indexOf(')');

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex){
            int count = Integer.parseInt(extractDigit.substring(startIndex+1, endIndex));
            System.out.println("Total Records: "+ count);

            if (count > 0){
                int index = 1;
                int pagination = 1;
                for (int i = 1; i <= count; i++){
                    if (index > 50){
                        // reset the index counter
                        index = 1;
                        // pagination increment
                        pagination++;
                        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//ul[@class='oxd-pagination__ul']/li)["+pagination+"]")).click();
                    }
                    String name = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("((//div[@class='oxd-table-body']/div/div)["+index+"]/div)[1]")).getText();
                    String stringHours= CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("((//div[@class='oxd-table-body']/div/div)["+index+"]/div)[2]")).getText();
                    employeeAttendanceRecords.put(name, Double.parseDouble(stringHours));
                    index++;
                }
            }else{
               Assert.fail("No records found!");
            }
        }else{
            System.out.println("No digits inside parentheses found.");
        }

        for (Map.Entry<String, Double> entry: employeeAttendanceRecords.entrySet()) {
            System.out.print(entry.getKey()+ " "+ entry.getValue()+ "\n");
        }
    }

    /**
     @@time_attendance_configuration
     @Scenario: Positive navigation to the Attendance configuration page
     @When User clicks on configuration
     @Then Configuration page appears
     */

    @When("User clicks on configuration")
    public void userClicksOnConfiguration() {
        WebElement configuration = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[4]"));
        configuration.click();
    }

    @Then("Configuration page appears")
    public void configurationPageAppears() {
        WebElement configurationPage = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
        if (configurationPage.isDisplayed()){
            Assert.assertTrue(true);
            System.out.println(configurationPage.getText());
        }else{
            Assert.fail("Configuration page not found!");
        }
    }

    @And("User unchecks all the option")
    public void userUnchecksAllTheOption() {
        List<WebElement> configurationList = CommonStepDefinition.browserClass.getWebDriver().findElements(By.xpath("((//div[@class='oxd-form-row']/div/div)/div)"));

        for (WebElement element: configurationList) {
            isChecked = element.isSelected();

            // unchecks all the selected options
            if (isChecked){
                element.click();
            }
        }
    }

    @And("User clicks on save")
    public void userClicksOnSave() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-form-actions']/button")).click();
    }

    @Then("User should be able to see the changes")
    public void userShouldBeAbleToSeeTheChanges() {
        Assert.assertFalse(isChecked, "Changes not made!");
    }
}
