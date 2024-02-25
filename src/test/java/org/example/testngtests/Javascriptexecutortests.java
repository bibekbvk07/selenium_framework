package org.example.testngtests;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Javascriptexecutortests {
    WebDriver driver;
    @BeforeTest
    public void beforetest() {
        CommonFunctions.invoke();
        driver = new ChromeDriver(CommonFunctions.getChromeOptions());
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    @Test
    public void scroll_test(){
        try{
            driver.get("https://www.tutorialspoint.com/index.htm");
            WebElement element = driver.findElement(By.xpath("//h5[text()='About us']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(3000);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
