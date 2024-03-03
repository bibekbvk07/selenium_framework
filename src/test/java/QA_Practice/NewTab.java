package QA_Practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewTab {

    WebDriver webDriver;

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(ops);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void newTab_test(){
        try{
            webDriver.get("https://demoqa.com/browser-windows");

            String currentWindowHandle = webDriver.getWindowHandle();

            WebElement newTab = webDriver.findElement(By.id("tabButton"));
            newTab.click();
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String windowHandle: webDriver.getWindowHandles()) {
                if (!currentWindowHandle.equals(windowHandle)){
                    webDriver.switchTo().window(windowHandle);
                    break;
                }
            }
            String urlOfNewTab = webDriver.getCurrentUrl();
            System.out.println(urlOfNewTab);
            if (urlOfNewTab.equals("https://demoqa.com/sample")){
                Assert.assertTrue(true);
            }else {
                Assert.fail("Did not switched to new tab");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Test
    public void new_tab_test(){
        try{
            webDriver.get("https://www.tutorialspoint.com/about/about_careers.htm");
            WebElement termsOfUse = webDriver.findElement(By.xpath("//*[text()='Terms of Use']"));
            termsOfUse.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
            Thread.sleep(1000);

            List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));

            String title = webDriver.getTitle();

            if (title.equals("Terms of Use")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Wrong tab");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @AfterTest
    public void aftertest(){
        webDriver.quit();
    }
}
