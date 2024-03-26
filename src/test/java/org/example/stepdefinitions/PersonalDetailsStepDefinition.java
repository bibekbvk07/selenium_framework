package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.example.locators.MyInfoLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.time.Duration;

public class PersonalDetailsStepDefinition {

    private final Actions actions = new Actions(CommonStepDefinition.browserClass.getWebDriver());
    private static int count;

    @Given("User clicks on {string} link and validates header {string}")
    public void userClicksOnAndValidates(String locator, String validator) {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        element.click();
        //Assert Validation
        WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoValidators(validator));
        Assert.assertEquals(elementValidation.getText(), validator, "Element was not a match!");
    }

    @When("User uploads the attachment url {string}")
    public void userUploadsTheAttachment(String url) {
        int lastIndex = url.lastIndexOf('/');
        String validation = "C:\\fakepath\\"+ url.substring(lastIndex + 1);

        WebElement uploadFile = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getFileUpload());
        File filePath = new File(url);
        uploadFile.sendKeys(filePath.getAbsolutePath());
        Assert.assertEquals(uploadFile.getAttribute("value"), validation, "Uploaded filepath is not the same!");
    }

    @Then("User {string} should be changed")
    public void userShouldBeChanged(String locator) {
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(10));
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed(), "Image is not displayed");
    }

    @When("User enters {string} input {string}")
    public void userEntersInput(String name, String input) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(By.name(name));
        //Clearing the input field, before populating with the user input
        while (!element.getAttribute("value").isEmpty()){
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(input);
        Assert.assertEquals(element.getAttribute("value"), input, "User input did not match!");
    }

    @And("User enters locator {string} input {string}")
    public void userEntersLocatorInput(String locator, String input) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        //Clearing the input, before populating with the user input
        while(!element.getAttribute("value").isEmpty()){
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(input);
        Assert.assertEquals(element.getAttribute("value"), input, "User input did not match!");
    }

    @And("User enters dropdown locator {string} input {string}")
    public void userEntersDropdownLocatorInput(String locator, String input) {
        input = input.toLowerCase();
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(15));
        WebElement dropdownElement = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        wait.until(ExpectedConditions.visibilityOf(dropdownElement));
        dropdownElement.click(); // click to activate the Keys.Down operation
        //Loop through the options until the desired option is found
        WebElement selectedOption = null;
        boolean optionFound = false;
        while (!optionFound){
            selectedOption = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
            String selectedOptionText = selectedOption.getText();
            // Compare the selected option text with the desired option
            if (selectedOptionText.equalsIgnoreCase(input)){
                optionFound = true;
                selectedOption.click(); // selects the option
            }else {
                // If not the desired option, will perform Keys.Down operation
                actions.sendKeys(Keys.DOWN).perform();
            }
        }
        Assert.assertEquals(selectedOption.getText().toLowerCase(), input, "Selection was not made!");
    }

    @Then("User clicks on {string} link and validates locator {string} value {string}")
    public void userClicksOnLinkAndValidatesLocatorValue(String locator, String validator, String value) {
        WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        element.click();
        //Assert Validation
        WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(validator));
        Assert.assertEquals(elementValidation.getText(), value, "Element was not a match!");
    }

    @And("User selects the gender {string}")
    public void userSelectsTheGenderM(String gender) {
        WebElement male = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input'])[1]"));
        WebElement female = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input'])[2]"));

        WebElement selectedChoice;

        if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")){
            selectedChoice = male;
        } else if (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")){
            selectedChoice = female;
        }else {
            throw new IllegalArgumentException("Invalid gender"+ gender);
        }

        //Clicks the selected choice
        if (!selectedChoice.isSelected()){
            selectedChoice.click();
        }else{
            Assert.assertTrue(true, "Already selected");
        }
    }

    @Given("User scrolls down to the {string} section and validates header {string}")
    public void userScrollsDownToTheSectionAndValidatesHeader(String locator, String validator) {
        WebElement scrollElement = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
        ((JavascriptExecutor) CommonStepDefinition.browserClass.getWebDriver()).executeScript("arguments[0].scrollIntoView(true)", scrollElement);
        WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoValidators(validator));
        Assert.assertEquals(elementValidation.getText(), validator, "Page was not scrolled!");
    }

    @Then("User should be able to see the attachment in the {string} field and validates with file name {string}")
    public void user_should_be_able_to_see_the_attachment_in_the_field_and_validates_with_file_name(String locator, String validator) {
        ((JavascriptExecutor) CommonStepDefinition.browserClass.getWebDriver()).executeScript("window.scrollBy(0,200)", "");
        WebElement recordsCount = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));

        String records = recordsCount.getText();
        int startIndex = records.indexOf("(");
        int endIndex = records.indexOf(")");

        String countString;
        if (startIndex >= 0 && endIndex >= 0){
            countString = records.substring(startIndex+1, endIndex);
        }else{
            countString ="0";
        }
        // Stores the record count in the class variable count for reference
        count = Integer.parseInt(countString);

        WebElement test = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-body']/div["+count+"]/div"));
        //Extracting text from the WebElement
        String [] attachmentRecords = test.getText().split("\\n");

        Assert.assertEquals(attachmentRecords[0], validator, "Attachment not found!");
    }

    @When("User clicks on the edit button of the record to be edited and validates header {string}")
    public void userClicksOnTheEditButtonOfTheRecordToBeEdited(String validator) {
        WebElement editButton = CommonStepDefinition.browserClass.getWebDriver()
                .findElement(By.xpath("(//div[@class='oxd-table-body']/div["+count+"]/div/div[8]/div/button[1])"));
        editButton.click();
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='orangehrm-card-container']/h6)[2]"));
        Assert.assertEquals(validation.getText(), validator, "Edit button was not clicked!" );
    }

    @Then("User should see the updated records and validates description {string}")
    public void userShouldBeAbleToSeeTheUpdatedRecords(String validator) {
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver()
                .findElement(By.xpath("//div[@class='oxd-table-body']/div["+count+"]/div/div[3]"));
        Assert.assertEquals(validation.getText(), validator, "Records was not Updated!");
    }

    @When("User clicks on the delete button of the record to be deleted and validates header {string}")
    public void userClicksOnTheDeleteButtonOfTheRecordToBeDeleted(String validator) {
        WebElement deleteButton = CommonStepDefinition.browserClass.getWebDriver()
                .findElement(By.xpath("(//div[@class='oxd-table-body']/div["+count+"]/div/div[8]/div/button[2])"));
        deleteButton.click();
        WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(10));
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-modal-header']/p"));
        wait.until(ExpectedConditions.visibilityOf(validation));
        Assert.assertEquals(validation.getText(), validator, "Delete button was not clicked!");
    }

    @Then("User selected record should be deleted")
    public void userSelectedRecordShouldBeDeleted() {
        WebElement recordsCount = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/span"));
        String records = recordsCount.getText();
        int startIndex = records.indexOf("(");
        int endIndex = records.indexOf(")");

        String countString;
        if (startIndex >= 0 && endIndex >= 0){
            countString = records.substring(startIndex+1, endIndex);
        }else {
            countString = "0";
        }
        int currentCount = Integer.parseInt(countString);

        if (count == 1){
            Assert.assertEquals(currentCount, 0, "Records was not deleted!");
        }else{
            Assert.assertEquals(currentCount, count-1, "Records was not deleted!");
        }
        count = currentCount; // Updating the actual count for reference
    }

    @Given("User selects all the records")
    public void userSelectsAllTheRecords() throws InterruptedException {
        Thread.sleep(2000);
        WebElement selectAll = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-header']/div/div[1]"));
        selectAll.click();
        WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@type='checkbox']"));
        if (validation.isSelected()){
            Assert.assertTrue(true);
        }else{
            Assert.fail("Not Selected!");
        }
    }

    @And("User clicks on {string} button and validates header {string}")
    public void userClicksOnDeleteSelectedButton(String locator, String validator) {
        if (count > 0){
            WebElement deleteButton = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator));
            deleteButton.click();

            WebDriverWait wait = new WebDriverWait(CommonStepDefinition.browserClass.getWebDriver(), Duration.ofSeconds(10));
            WebElement validation = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-modal-header']/p"));
            wait.until(ExpectedConditions.visibilityOf(validation));
            Assert.assertEquals(validation.getText(), validator, "Delete button was not clicked!");
        }else{
            Assert.assertTrue(true);
        }
    }

    @And("User clicks on {string} link if records available and validates locator {string} value {string}")
    public void userClicksOnLinkIfRecordsAvailableAndValidatesLocatorValue(String locator1, String locator2, String value) {
        if (count > 0){
            WebElement element = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator1));
            element.click();
            //Assert Validation
            WebElement elementValidation = CommonStepDefinition.browserClass.getWebDriver().findElement(MyInfoLocator.getMyInfoLocators(locator2));
            Assert.assertEquals(elementValidation.getText(), value, "Element was not a match!");
        }else{
            Assert.assertTrue(true);
        }
    }

    @Then("All records should be deleted and count should be {int}")
    public void allRecordsShouldBeDeleted(int validator) {
        WebElement recordsCount = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/span"));
        String records = recordsCount.getText();
        int startIndex = records.indexOf("(");
        int endIndex = records.indexOf(")");

        String countString;
        if (startIndex >= 0 && endIndex >= 0){
            countString = records.substring(startIndex+1, endIndex);
        }else{
            countString = "0";
        }
        count = Integer.parseInt(countString);
        Assert.assertEquals(count, validator, "Records still Available?");
    }
}
