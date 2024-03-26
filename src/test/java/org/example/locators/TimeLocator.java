package org.example.locators;

import org.openqa.selenium.By;

public class TimeLocator {
    private static final By TIME_SHEETS_LOCATOR = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[1]");
    private static final By ATTENDANCE_LOCATOR = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[2]");
    private static final By REPORTS_LOCATOR = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[3]");
    private static final By PROJECT_INFO_LOCATOR = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-item'])[4]");
    private static final By MY_TIME_SHEETS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[1]");
    private static final By EMPLOYEE_TIME_SHEETS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[2]");
    private static final By MY_RECORDS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[1]");
    private static final By PUNCH_IN_OUT_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[2]");
    private static final By EMPLOYEE_RECORDS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[3]");
    private static final By CONFIGURATION_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[4]");
    private static final By PROJECT_REPORTS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[1]");
    private static final By EMPLOYEE_REPORTS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[2]");
    private static final By ATTENDANCE_SUMMARY_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[3]");
    private static final By CUSTOMERS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[1]");
    private static final By PROJECTS_DROPDOWN_MENU = By.xpath("(//*[@class='oxd-topbar-body-nav-tab-link'])[2]");
    private static final By DROP_DOWN_HEADER_LOCATOR = By.xpath("//*[@class='oxd-table-filter-header-title']/*");
    private static final By PUNCH_IN_OUT_HEADER_LOCATOR = By.xpath("//*[@class='orangehrm-card-container']/h6");
    private static final By CONFIGURATION_HEADER_LOCATOR = By.xpath("//*[@class='orangehrm-card-container']/h6");

    public static By getTimeLocators(String locator){
        locator = locator.replaceAll("\\s", "_");
        return switch (locator.toUpperCase()){
            case "TIME_SHEETS" -> TIME_SHEETS_LOCATOR;
            case "ATTENDANCE" -> ATTENDANCE_LOCATOR;
            case "REPORTS" -> REPORTS_LOCATOR;
            case "PROJECT_INFO" -> PROJECT_INFO_LOCATOR;
            case "MY_TIME_SHEETS" -> MY_TIME_SHEETS_DROPDOWN_MENU;
            case "EMPLOYEE_TIME_SHEETS" -> EMPLOYEE_TIME_SHEETS_DROPDOWN_MENU;
            case "MY_RECORDS" -> MY_RECORDS_DROPDOWN_MENU;
            case "PUNCH_IN_OUT" -> PUNCH_IN_OUT_DROPDOWN_MENU;
            case "EMPLOYEE_RECORDS" -> EMPLOYEE_RECORDS_DROPDOWN_MENU;
            case "CONFIGURATION" -> CONFIGURATION_DROPDOWN_MENU;
            case "PROJECT_REPORTS" -> PROJECT_REPORTS_DROPDOWN_MENU;
            case "EMPLOYEE_REPORTS" -> EMPLOYEE_REPORTS_DROPDOWN_MENU;
            case "ATTENDANCE_SUMMARY" -> ATTENDANCE_SUMMARY_DROPDOWN_MENU;
            case "CUSTOMERS" -> CUSTOMERS_DROPDOWN_MENU;
            case "PROJECTS" -> PROJECTS_DROPDOWN_MENU;
            case "MY_ATTENDANCE_RECORDS", "EMPLOYEE_ATTENDANCE_RECORDS" -> DROP_DOWN_HEADER_LOCATOR;
            case "PUNCH_OUT" -> PUNCH_IN_OUT_HEADER_LOCATOR;
            case "ATTENDANCE_CONFIGURATION" -> CONFIGURATION_HEADER_LOCATOR;
             default -> throw new IllegalStateException("Unexpected value: " + locator.toUpperCase());
        };
    }
}
