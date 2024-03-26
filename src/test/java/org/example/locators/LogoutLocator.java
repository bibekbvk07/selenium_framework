package org.example.locators;

import org.openqa.selenium.By;

public class LogoutLocator {
    private static final By LOGOUT_LOCATOR = By.xpath("//*[contains(@href, '/logout')]");

    private static final By USER_DROPDOWN_LOCATOR = By.className("oxd-userdropdown-tab");

    public static By getLogoutLocator(){
        return LOGOUT_LOCATOR;
    }
    public static By getUserDropdownLocator(){
        return USER_DROPDOWN_LOCATOR;
    }

}
