package org.example.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class CommonFunctions {
     private static ChromeOptions ops;

    public static void webDriverManager(){
//        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath + "/src/test/resources/webdriver/chromedriver");
    }
    public static void invoke(){
        try {
            webDriverManager();
            ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            ops.addArguments("--start-maximized");
            ops.addArguments("--incognito");
            ops.addArguments("--disable-geolocation");
            ops.setExperimentalOption("useAutomationExtension", false);
            ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ChromeOptions getChromeOptions(){
        return ops;
    }
    public static void sendKeys(WebDriver driver, By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }

    public static void scrollToElements(WebDriver webDriver, WebElement element){
        try{
            // Use JavaScriptExecutor interface to scroll to the element
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", element);
            // Optionally you can add a delay to allow time for the scroll to complete
            Thread.sleep(3000);
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public static void click(By locator, WebDriver driver){
        try{
            WebElement element = driver.findElement(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            /**
             * The 'driver'object is cast to the 'JavascriptExecutor' interface.The JavascriptExecutor interface provides
             * the executeScript method, allowing you to execute JavaScript code in the context of the current browsing window.

             -->    The executeScript method is called, and it takes two parameters:
                        * The first parameter is a string containing the JavaScript code to be executed.
                        * The second parameter is the element on which the JavaScript code will be executed.
             */

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
             element.click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
