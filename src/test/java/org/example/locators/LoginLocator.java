package org.example.locators;

import org.openqa.selenium.By;

public class LoginLocator {
    private static final By LOGIN_LOCATOR = By.xpath("//*[@class='orangehrm-login-slot']/h5");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final By INVALID_LOGIN_LOCATOR = By.xpath("//div[@class='orangehrm-login-error']/div[1]");
    private static final By FORGOT_PASSWORD_LOCATOR = By.xpath("//*[@class='orangehrm-login-forgot']/*");
    private static final By VALID_LOGIN_VALIDATOR = By.xpath("//*[@class='oxd-topbar-header-breadcrumb']/h6");
    public static By getLoginLocator() {
        return LOGIN_LOCATOR;
    }
    public static By getLoginButtonLocator(){
        return LOGIN_BUTTON_LOCATOR;
    }
    public static By getInvalidLoginLocator(){
        return INVALID_LOGIN_LOCATOR;
    }
    public static By getValidLoginValidator(){
        return VALID_LOGIN_VALIDATOR;
    }
}
