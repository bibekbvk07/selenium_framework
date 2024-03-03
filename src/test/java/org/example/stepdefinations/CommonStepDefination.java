package org.example.stepdefinations;

import io.cucumber.java.*;
import org.example.utilities.SingletonBrowserClass;

public class CommonStepDefination {
    public static SingletonBrowserClass browserClass;

    /**
     * Setup method to initialize the browser instance before all test methods.
     */
    @BeforeAll
    public static void setup(){
        browserClass = SingletonBrowserClass.getSingleBrowserInstance();
    }
    /**
     You can log information or capture screenshots after each test scenario to aid in debugging or reporting. For instance, if your test fails, you can take a screenshot of the browser window.
     */
    @After
    public static void after(){
        // code to take screenshot of failed and passed test cases
        CommonStepDefination.browserClass.getWebDriver().close();
    }
    /**
     * Teardown method to quit the WebDriver after all test methods.
     */
    @AfterAll
    public static void teardown(){
        // Close the browser
        if (browserClass != null){
            browserClass.getWebDriver().quit();
        }
    }
}
