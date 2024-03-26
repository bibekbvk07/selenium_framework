package org.example.locators;

import org.openqa.selenium.By;

public class SidePanelLocator {

    /**=========================  SIDE_PANEL_NAVBAR_XPATH_LOCATORS =======================================================================*/
    private static final By SEARCH_BAR = By.xpath("//*[@class='oxd-main-menu-search']/input");
    private static final By SIDE_PANEL_EXPAND_COLLAPSE_BUTTON = By.xpath("//*[@class='oxd-main-menu-search']/button");
    private static final By ADMIN_LOCATOR = By.xpath("//*[contains(@href, '/admin/viewAdminModule')]");
    private static final By PIM_LOCATOR = By.xpath("//*[contains(@href, '/pim/viewPimModule')]");
    private static final By LEAVE_LOCATOR = By.xpath("//*[contains(@href, '/leave/viewLeaveModule')]");
    private static final By TIME_LOCATOR = By.xpath("//*[contains(@href, '/time/viewTimeModule')]");
    private static final By RECRUITMENT_LOCATOR = By.xpath("//*[contains(@href, '/recruitment/viewRecruitmentModule')]");
    private static final By MY_INFO_LOCATOR = By.xpath("//*[contains(@href, '/pim/viewMyDetails')]");
    private static final By PERFORMANCE_LOCATOR = By.xpath("//*[contains(@href, '/performance/viewPerformanceModule')]");
    private static final By DASHBOARD_LOCATOR = By.xpath("//*[contains(@href, '/dashboard/index')]");
    private static final By DIRECTORY_LOCATOR = By.xpath("//*[contains(@href, '/directory/viewDirectory')]");
    private static final By MAINTENANCE_LOCATOR = By.xpath("//*[contains(@href, '/maintenance/viewMaintenanceModule')]");
    private static final By CLAIM_LOCATOR = By.xpath("//*[contains(@href, '/claim/viewClaimModule')]");
    private static final By BUZZ_LOCATOR = By.xpath("//*[contains(@href, '/buzz/viewBuzz')]");
    private static final By CONFIRM_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");

    /**=========================  SIDE_PANEL_NAVBAR_XPATH_VALIDATORS =======================================================================*/
    private static final By SIDE_PANEL_HEADER_VALIDATOR = By.xpath("//*[@class='oxd-topbar-header-breadcrumb']/h6");

    public static By getSidePanelLocators(String locator){
        locator = locator.replaceAll("\\s", "_");
        return switch (locator.toUpperCase()){
            case "SEARCH_BAR" -> SEARCH_BAR;
            case "SIDE_PANEL_EXPAND" -> SIDE_PANEL_EXPAND_COLLAPSE_BUTTON;
            case "ADMIN" -> ADMIN_LOCATOR;
            case "PIM" ->PIM_LOCATOR;
            case "LEAVE" -> LEAVE_LOCATOR;
            case "TIME" -> TIME_LOCATOR;
            case "RECRUITMENT" -> RECRUITMENT_LOCATOR;
            case "MY_INFO" -> MY_INFO_LOCATOR;
            case "PERFORMANCE" -> PERFORMANCE_LOCATOR;
            case "DASHBOARD" -> DASHBOARD_LOCATOR;
            case "DIRECTORY" -> DIRECTORY_LOCATOR;
            case "MAINTENANCE" -> MAINTENANCE_LOCATOR;
            case "CLAIM" -> CLAIM_LOCATOR;
            case "BUZZ" -> BUZZ_LOCATOR;
            case "CONFIRM_BUTTON" -> CONFIRM_BUTTON_LOCATOR;
            default -> throw new IllegalStateException("Unexpected value: " + locator.toUpperCase());
        };
    }
    public static By getSidePanelHeaderValidator(){
        return SIDE_PANEL_HEADER_VALIDATOR;
    }
}
