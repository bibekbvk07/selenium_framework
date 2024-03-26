package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.example.globalfiles.GlobalFile;
import org.example.locators.TimeLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeStepDefinition {

    private static final Actions actions = new Actions(CommonStepDefinition.browserClass.getWebDriver());
    private boolean isChecked;

    @Given("User hovers and clicks on {string} and validates header {string}")
    public void user_hovers_and_clicks_on_attendance(String locator, String validator) throws InterruptedException {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(TimeLocator.getTimeLocators(locator));
        actions.moveToElement(element).build().perform();
        element.click();
        // Validation with Asserts
        Thread.sleep(2000);
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(TimeLocator.getTimeLocators(validator));
        Assert.assertEquals(validation.getText(), validator, "Element was not a match!");
    }

    @When("User clicks on {string} dropdown menu and validates header {string}")
    public void userClicksOnDropdownMenuAndValidatesHeader(String locator, String validator) {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(TimeLocator.getTimeLocators(locator));
        element.click();
        // Validation with Asserts
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(TimeLocator.getTimeLocators(validator));
        Assert.assertEquals(validation.getText(), validator, "Element was not a match!");
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
