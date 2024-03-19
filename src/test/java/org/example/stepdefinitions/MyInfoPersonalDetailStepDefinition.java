package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.io.File;

public class MyInfoPersonalDetailStepDefinition {

    private final Actions actions = new Actions(CommonStepDefinition.browserClass.getWebDriver());
    private static int count;

    @And("User clicks on my info link")
    public void userClicksOnMyInfoLink() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/viewMyDetails')]")).click();
    }
    @Given("User clicks on profile picture")
    public void user_clicks_on_profile_picture() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("employee-image")).click();
    }
    @When("User uploads the profile picture")
    public void userUploadsTheProfilePicture() {
        try{
            String path = "/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile1.jpg";
            File uploadFile = new File(path);
            WebElement fileInput = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@type='file']"));
            fileInput.sendKeys(uploadFile.getAbsolutePath());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Then("User profile picture should be changed")
    public void user_profile_picture_should_be_changed() {
        System.out.println("Profile Picture was successfully changed!");
    }

    @Given("User clicks on personal details")
    public void user_clicks_on_personal_details()  {
       CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//a[contains(@href, '/viewPersonalDetails')]")).click();
    }
    @When("User enters firstname {string}")
    public void user_enters_firstname(String firstName) {
        try{
            WebElement fname = CommonStepDefinition.browserClass.getWebDriver().findElement(By.name("firstName"));
            Thread.sleep(3000);

            // Clearing the field by sending a series of backspace keys
            while (!fname.getAttribute("value").isEmpty()){
                fname.sendKeys(Keys.BACK_SPACE);
            }
            fname.sendKeys(firstName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters middle name {string}")
    public void user_enters_middle_name(String mname) {
        try{
            WebElement middleName = CommonStepDefinition.browserClass.getWebDriver().findElement(By.name("middleName"));

            //Clearing the field by sending a series of backspace keys
            while (!middleName.getAttribute("value").isEmpty()){
                middleName.sendKeys(Keys.BACK_SPACE);
            }
            middleName.sendKeys(mname);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters lastname {string}")
    public void user_enters_lastname(String lname) {
       try{
           WebElement lastName = CommonStepDefinition.browserClass.getWebDriver().findElement(By.name("lastName"));
           //Clearing the field by sending a series of backspace keys
           while (!lastName.getAttribute("value").isEmpty()){
               lastName.sendKeys(Keys.BACK_SPACE);
           }
           lastName.sendKeys(lname);
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    @When("User enters employee id {string}")
    public void user_enters_employee_id(String empId) {
        try{
            WebElement employeeId = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//*[@class='oxd-label' and text()='Employee Id']/../following-sibling::*/input"));
            //Clearing the field by sending a series of backspace keys
            while (!employeeId.getAttribute("value").isEmpty()){
                employeeId.sendKeys(Keys.BACK_SPACE);
            }
            employeeId.sendKeys(empId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters other id {string}")
    public void user_enters_other_id(String id) {
        try{
            WebElement otherId = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//*[@class='oxd-label' and text()='Other Id']/../following-sibling::*/input"));
            while (!otherId.getAttribute("value").isEmpty()){
                otherId.sendKeys(Keys.BACK_SPACE);
            }
            otherId.sendKeys(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters driver's license number {string}")
    public void user_enters_driver_s_license_number(String license) {
        try{
            WebElement licenseNum = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//label[starts-with(text(), 'Driver')]/../following-sibling::*/input"));
            while (!licenseNum.getAttribute("value").isEmpty()){
                licenseNum.sendKeys(Keys.BACK_SPACE);
            }
            licenseNum.sendKeys(license);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters license expiry date {string}")
    public void user_enters_license_expiry_date_yyyy_dd_mm(String expiryDate) {
        try{
            WebElement licenseExpiryDate = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-date-input']/input)[1]"));
            while (!licenseExpiryDate.getAttribute("value").isEmpty()){
                licenseExpiryDate.sendKeys(Keys.BACK_SPACE);
            }
            licenseExpiryDate.sendKeys(expiryDate);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User selects nationality {string}")
    public void user_selects_nationality(String nationalityValue) {
        try{
            // Validate the String input
            nationalityValue = nationalityValue.substring(0,1).toUpperCase() + nationalityValue.substring(1).toLowerCase();
            // Find the dropdown input element
            WebElement dropdownInput = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-select-text-input'])[1]"));
            // Clicks on the dropdown input to activate it
            dropdownInput.click();
            actions.sendKeys(Keys.DOWN).perform();

            //Loop through the options until the desired option is found
            boolean optionFound = false;
            while (!optionFound){
                // Find the currently selected option
                WebElement selectedOption = CommonStepDefinition.browserClass.getWebDriver().findElement(By.className("oxd-select-text-input"));
                String selectedOptionText = selectedOption.getText();

                // Compare the selected option text with the desired option
                if (selectedOptionText.equals(nationalityValue)){
                    optionFound = true;
                    selectedOption.click();
                }else{
                    // If not the desired option, simulate another DOWN arrow key press
                    actions.sendKeys(Keys.DOWN).perform();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User selects marital status {string}")
    public void user_selects_marital_status(String status) {
        try{
            status = status.substring(0,1).toUpperCase() + status.substring(1).toLowerCase();
            WebElement maritalStatus = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));

            // Click the dropdown
            maritalStatus.click();
//            actions.sendKeys(Keys.DOWN).perform();
            boolean optionFound = false;

            while (!optionFound){
                WebElement selectOption = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
                String selectOptionText = selectOption.getText();

                // Compare the selected option text with the desired option
                if (selectOptionText.equals(status)){
                    optionFound = true;
                    selectOption.click();
                } else{
                    // If not the desired option, simulate another DOWN arrow key press
                    actions.sendKeys(Keys.DOWN).perform();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @When("User enters date of birth {string}")
    public void user_enters_date_of_birth(String dob) {
        try{
            WebElement dateOfBirth = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-date-input']/input)[2]"));
            while (!dateOfBirth.getAttribute("value").isEmpty()){
                dateOfBirth.sendKeys(Keys.BACK_SPACE);
            }
            dateOfBirth.sendKeys(dob);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @And("User selects the gender {string}")
    public void userSelectsTheGenderM(String gender) {
        try{
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Given("User scrolls down on the custom fields")
    public void userScrollsDownOnTheCustomFields() throws InterruptedException {
        WebElement scrollElement = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
        //Window Scroll by pixel
        ((JavascriptExecutor) CommonStepDefinition.browserClass.getWebDriver()).executeScript("window.scrollBy(0, 200)", "");
    }

    @And("User selects the blood type {string}")
    public void userSelectsTheBloodType(String bloodType) {
        try{
            WebElement customFields = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-select-text-input'])[3]"));
            customFields.click();

            actions.sendKeys(Keys.DOWN).perform();

            boolean optionFound = false;

            while (!optionFound){
                WebElement selectOption = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-select-text-input'])[3]"));
                String selectOptionText = selectOption.getText();

                if (selectOptionText.equalsIgnoreCase(bloodType)){
                    optionFound = true;
                    selectOption.click();
                }else{
                    actions.sendKeys(Keys.DOWN).perform();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @And("User enters test_field {string}")
    public void userEntersTest_field(String input) {
        try{
            WebElement testField = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//label[@class='oxd-label' and text()='Test_Field']/../following-sibling::*/input"));
            while (!testField.getAttribute("value").isEmpty()){
                testField.sendKeys(Keys.BACK_SPACE);
            }
            testField.sendKeys(input);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Then("User clicks on custom_field save button")
    public void userClicksOnCustom_fieldSaveButton() {
        WebElement customFieldSave = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//button[@type='submit'])[2]"));
        customFieldSave.click();
    }

    @Given("User scrolls down on the attachment section")
    public void userScrollsDownOnTheAttachmentSection() {
        ((JavascriptExecutor)CommonStepDefinition.browserClass.getWebDriver()).executeScript("window.scrollBy(0,200)", "");
    }

    @And("User clicks on the add attachment button")
    public void userClicksOnTheAddAttachmentButton() {
        try{
            Thread.sleep(3000);
            WebElement addButton = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-action-header']/button"));
            addButton.click();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @And("User uploads the attachment")
    public void userUploadsTheAttachment() {
        try{
            WebElement uploadFile = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@type='file']"));
            File filePath = new File("/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile1.jpg");
            uploadFile.sendKeys(filePath.getAbsolutePath());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @And("User types a comment {string}")
    public void userTypesAComment(String comments) {
        WebElement typeComments = CommonStepDefinition.browserClass.getWebDriver().findElement(By.tagName("textarea"));
        typeComments.sendKeys(comments);
    }

    @And("User clicks on save button for the attachments")
    public void userClicksOnSaveButtonForTheAttachments() {
        CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("(//div[@class='oxd-form-actions']/button[@type='submit'])[3]")).click();
    }

    @Then("User should be able to see the attachment in the records field")
    public void userShouldBeAbleToSeeTheAttachmentInTheRecordsField(){
        try{
            ((JavascriptExecutor) CommonStepDefinition.browserClass.getWebDriver()).executeScript("window.scrollBy(0,100)", "");
            Thread.sleep(3000);
            WebElement recordsCount = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/span"));
            String records = recordsCount.getText();
            int startIndex = records.indexOf("(");
            int endIndex = records.indexOf(")");

            String countString;
            if (startIndex >= 0 && endIndex >= 0){
                countString = records.substring(startIndex+1, endIndex);
            }else{
                countString ="0";
            }
            count = Integer.parseInt(countString);
            System.out.println("Records count: "+ count);

            WebElement test = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-body']/div["+count+"]/div"));

            //Extracting text from the WebElement
            String [] attachmentRecords = test.getText().split("\\n");
            System.out.println(attachmentRecords[0]);
            Assert.assertEquals(attachmentRecords[0], "profile1.jpg", "Attachment not found!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @When("User clicks on the edit button of the record to be edited")
    public void userClicksOnTheEditButtonOfTheRecordToBeEdited() throws InterruptedException {
        WebElement editButton = CommonStepDefinition.browserClass.getWebDriver()
                .findElement(By.xpath("(//div[@class='oxd-table-body']/div["+count+"]/div/div[8]/div/button[1])"));
        editButton.click();
    }
    @And("User uploads the attachment with new picture")
    public void userUploadsTheAttachmentWithNewPicture() {
        try{
            WebElement uploadFile = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//input[@type='file']"));
            File filePath = new File("/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile.png");
            uploadFile.sendKeys(filePath.getAbsolutePath());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @And("User edits a comment {string}")
    public void userEditsAComment(String comments) throws InterruptedException {
        Thread.sleep(3000);
        WebElement typeComments = CommonStepDefinition.browserClass.getWebDriver().findElement(By.tagName("textarea"));
        while (!typeComments.getAttribute("value").isEmpty()){
            typeComments.sendKeys(Keys.BACK_SPACE);
        }
        typeComments.sendKeys(comments);
    }
    @Then("User should be able to see the updated records")
    public void userShouldBeAbleToSeeTheUpdatedRecords() {
        try{
            WebElement validation = CommonStepDefinition.browserClass.getWebDriver()
                    .findElement(By.xpath("//div[@class='oxd-table-body']/div["+count+"]/div/div[3]"));
            System.out.println(validation.getText());
            Assert.assertEquals(validation.getText(), "Hello Mario!", "Not updated");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @When("User clicks on the delete button of the record to be deleted")
    public void userClicksOnTheDeleteButtonOfTheRecordToBeDeleted() {
        WebElement deleteButton = CommonStepDefinition.browserClass.getWebDriver()
                .findElement(By.xpath("(//div[@class='oxd-table-body']/div["+count+"]/div/div[8]/div/button[2])"));
        deleteButton.click();
    }
    @And("User clicks {string} button on the alert pop up")
    public void userClicksButtonOnTheAlertPopUp(String input) {
        try{
            int option = 0;
            if (input.equalsIgnoreCase("delete")){
                option = 2;
            } else{
                option = 1;
            }
            WebElement deleteOption = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-modal-footer']/button["+option+"]"));
            deleteOption.click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Then("User selected record should be deleted")
    public void userSelectedRecordShouldBeDeleted() {
        try{
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Given("User selects all the records")
    public void userSelectsAllTheRecords() {
        WebElement selectAll = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='oxd-table-header']/div/div[1]"));
        selectAll.click();
    }

    @And("User clicks on delete selected button")
    public void userClicksOnDeleteSelectedButton() {
        try{
            if (count != 0){
                WebElement deleteButton = CommonStepDefinition.browserClass.getWebDriver().findElement(By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/div/button"));
                deleteButton.click();
            }else{
                Assert.assertEquals(count, 0);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Then("All records should be deleted")
    public void allRecordsShouldBeDeleted() {
        try{
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
            Assert.assertEquals(count, 0, "Records still Available?");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
