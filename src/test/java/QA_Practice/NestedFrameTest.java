package QA_Practice;

import org.example.utilities.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class NestedFrameTest {

    SingletonBrowserClass instance;
    WebDriver driver;

    @BeforeTest
    public void beforetest(){
        instance = SingletonBrowserClass.getSingleBrowserInstance();
        driver = instance.getWebDriver();
        driver.get("https://demoqa.com/nestedframes");
    }
    @Test
    public void nested_frame_test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame1"));

        System.out.println(driver.findElement(By.id("frame1")).getText());

        WebElement childFrame = driver.findElement(By.xpath("//iframe[@srcdoc]"));
        driver.switchTo().frame(childFrame);
        System.out.println(childFrame.getText());

    }
    @AfterTest
    public void aftertest(){
        driver.quit();
    }
}
