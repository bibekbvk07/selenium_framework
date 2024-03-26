package org.example.locators;

import org.openqa.selenium.By;

public class MyInfoLocator {

    /**=========================  MY INFO SECTION XPATH LOCATORS =======================================================================*/
    private static final By MY_INFO = By.xpath("//*[contains(@href, '/pim/viewMyDetails')]");
    private static final By PROFILE_PICTURE = By.xpath("(//*[@class='employee-image'])[1]");
    private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");
    private static final By FILE_UPLOAD = By.xpath("//input[@type='file']");

    /****===========================MY INFO SECTION XPATH VALIDATORS ====================================================*/
    private static final By PROFILE_PICTURE_VALIDATOR = By.xpath("//*[@class='orangehrm-edit-employee-content']/*/h6");
    private static final By MY_INFO_VALIDATOR = By.xpath("//*[@class='oxd-topbar-header-breadcrumb']/h6");


    /****====================== PERSONAL DETAILS SECTION XPATH LOCATORS =====================================================*/
    private static final By PERSONAL_DETAILS = By.xpath("//*[contains(@href, '/pim/viewPersonalDetails')]");
    private static final By EMPLOYEE_ID = By.xpath("//*[@class='oxd-label' and text()='Employee Id']/../following-sibling::*/input");
    private static final By OTHER_ID = By.xpath("//*[@class='oxd-label' and text()='Other Id']/../following-sibling::*/input");
    private static final By DRIVER_LICENSE_NUMBER = By.xpath("//label[starts-with(text(), 'Driver')]/../following-sibling::*/input");
    private static final By LICENSE_EXPIRY_DATE = By.xpath("(//div[@class='oxd-date-input']/input)[1]");
    private static final By NATIONALITY = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private static final By MARITAL_STATUS = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private static final By DATE_OF_BIRTH = By.xpath("(//div[@class='oxd-date-input']/input)[2]");
    private static final By PERSONAL_DETAILS_SAVE_BUTTON = By.xpath("(//button[@type='submit'])[1]");
    private static final By BLOOD_TYPE = By.xpath("(//div[@class='oxd-select-text-input'])[3]");
    private static final By TEST_FIELD = By.xpath("//*[@class='oxd-label' and text()='Test_Field']/../following-sibling::*/input");
    private static final By CUSTOM_FIELD_SAVE_BUTTON = By.xpath("(//button[@type='submit'])[2]");
    private static final By TEXTAREA = By.tagName("textarea");
    private static final By ATTACHMENT = By.xpath("//*[@class='orangehrm-action-header']/h6");
    private static final By ADD_ATTACHMENT_BUTTON = By.xpath("(//button[@type='button'])[3]");
    private static final By ADD_ATTACHMENT = By.xpath("//*[@class='orangehrm-card-container']/h6[text()='Add Attachment']");
    private static final By ADD_ATTACHMENT_SAVE_BUTTON = By.xpath("(//button[@type='submit'])[3]");
    private static final By SCROLL_TO_CUSTOM_FIELDS = By.className("orangehrm-custom-fields");
    private static final By SCROLL_TO_ATTACHMENT = By.className("orangehrm-attachment");
    private static final By RECORD_LOCATOR = By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/span");
    private static final By EDIT_ATTACHMENT_SAVE_BUTTON = By.xpath("(//button[@type='submit'])[3]");
    private static final By CANCEL_DELETE_LOCATOR = By.xpath("//*[@class='orangehrm-modal-footer']/button[1]");
    private static final By YES_DELETE_LOCATOR = By.xpath("//*[@class='orangehrm-modal-footer']/button[2]");
    private static final By DELETE_SELECTED_LOCATOR = By.xpath("//div[@class='orangehrm-attachment']/div[2]/div/div/button");

    /****===========================PERSONAL DETAILS SECTION XPATH VALIDATORS ====================================================*/
    private static final By PERSONAL_DETAILS_VALIDATOR = By.xpath("//*[@class='orangehrm-edit-employee-content']/*/h6");
    private static final By CUSTOM_FIELDS_VALIDATOR = By.xpath("//*[@class='orangehrm-card-container']/h6");
    private static final By ATTACHMENT_VALIDATOR = By.xpath("//*[@class='orangehrm-action-header']/h6");
    private static final By EDIT_ATTACHMENT_VALIDATOR = By.xpath("(//*[@class='orangehrm-card-container']/h6)[2]");

    /** ===================================== SECTION ENDS ============================================================== */

    // Get method for locators
    public static By getMyInfoLocators(String locator){
        // Regex pattern '\s' to match any whitespace character, including spaces, tabs, and line breaks.
        locator = locator.replaceAll("\\s","_");

        return switch (locator.toUpperCase()) {
            case "PROFILE_PICTURE" -> PROFILE_PICTURE;
            case "SAVE_BUTTON" -> SAVE_BUTTON;
            case "FILE_UPLOAD" -> FILE_UPLOAD;
            case "MY_INFO" -> MY_INFO;
            case "PERSONAL_DETAILS" -> PERSONAL_DETAILS;
            case "EMPLOYEE_ID" -> EMPLOYEE_ID;
            case "OTHER_ID" -> OTHER_ID;
            case "DRIVER_LICENSE_NUMBER" -> DRIVER_LICENSE_NUMBER;
            case "LICENSE_EXPIRY_DATE" -> LICENSE_EXPIRY_DATE;
            case "NATIONALITY" -> NATIONALITY;
            case "MARITAL_STATUS" -> MARITAL_STATUS;
            case "DATE_OF_BIRTH" -> DATE_OF_BIRTH;
            case "PERSONAL_DETAILS_SAVE_BUTTON" -> PERSONAL_DETAILS_SAVE_BUTTON;
            case "CUSTOM_FIELDS" -> SCROLL_TO_CUSTOM_FIELDS;
            case "ATTACHMENT_FIELD" -> SCROLL_TO_ATTACHMENT;
            case "BLOOD_TYPE" -> BLOOD_TYPE;
            case "TEST_FIELD" -> TEST_FIELD;
            case "CUSTOM_FIELD_SAVE_BUTTON" -> CUSTOM_FIELD_SAVE_BUTTON;
            case "TEXTAREA" -> TEXTAREA;
            case "ATTACHMENT" -> ATTACHMENT;
            case "ADD_ATTACHMENT_BUTTON" -> ADD_ATTACHMENT_BUTTON;
            case "ADD_ATTACHMENT" -> ADD_ATTACHMENT;
            case "ADD_ATTACHMENT_SAVE_BUTTON" -> ADD_ATTACHMENT_SAVE_BUTTON;
            case "RECORDS" -> RECORD_LOCATOR;
            case "EDIT_ATTACHMENT_SAVE_BUTTON" -> EDIT_ATTACHMENT_SAVE_BUTTON;
            case "CANCEL_BUTTON" -> CANCEL_DELETE_LOCATOR;
            case "DELETE_BUTTON" -> YES_DELETE_LOCATOR;
            case "DELETE_SELECTED" -> DELETE_SELECTED_LOCATOR;
            default -> throw new IllegalStateException("Unexpected value: " + locator.toUpperCase());
        };
    }

    // Get method for validators
    public static By getMyInfoValidators(String validator){
        return switch (validator) {
            case "Change Profile Picture" -> PROFILE_PICTURE_VALIDATOR;
            case "PIM" -> MY_INFO_VALIDATOR;
            case "Personal Details" -> PERSONAL_DETAILS_VALIDATOR;
            case "Custom Fields"-> CUSTOM_FIELDS_VALIDATOR;
            case "Attachments" -> ATTACHMENT_VALIDATOR;
            case "edit attachment" -> EDIT_ATTACHMENT_VALIDATOR;
            default -> throw new IllegalStateException("Unexpected value: " +validator);
        };
    }

    public static By getFileUpload() {
        return FILE_UPLOAD;
    }
}
