package QA_Practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Alerts {
    WebDriver webDriver;

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--incognito");
        ops.addArguments("--start-maximized");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(ops);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://demoqa.com/alerts");
    }
    @Test
    public void alert_popUp_test(){
        try{
            webDriver.findElement(By.xpath("//button[@id='alertButton']")).click();
            Thread.sleep(1000);
            System.out.println(webDriver.switchTo().alert().getText());
            webDriver.switchTo().alert().accept();
        }catch(Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void timerAlertTest(){
        try {
            webDriver.findElement(By.id("timerAlertButton")).click();
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(2000);
            System.out.println(webDriver.switchTo().alert().getText());
            webDriver.switchTo().alert().accept();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void confirmAlert_test(){
        try{
            webDriver.findElement(By.id("confirmButton")).click();
            Thread.sleep(3000);
            System.out.println(webDriver.switchTo().alert().getText());
            webDriver.switchTo().alert().accept();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void dismissAlert_test(){
        try{
            webDriver.findElement(By.id("confirmButton")).click();
            Thread.sleep(3000);
            System.out.println(webDriver.switchTo().alert().getText());
            webDriver.switchTo().alert().dismiss();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Test
    public void alertEnterText(){
        try{
            webDriver.findElement(By.id("promtButton")).click();
            Thread.sleep(1000);
            Alert alerts = webDriver.switchTo().alert();
            System.out.println(alerts.getText());
            alerts.sendKeys("Hello World!");
            Thread.sleep(3000);
            alerts.accept();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @AfterTest
    public void aftertest(){
        webDriver.quit();
    }
}
