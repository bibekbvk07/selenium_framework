package QA_Practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NikeTest {

    WebDriver webDriver;
    Actions actions;

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
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        actions = new Actions(webDriver);
    }
    /**
    This test will hover over all the elements in the menu bar.
     */
    @Test
    public void hover_thru_menuBar(){
        try{
            webDriver.get("https://www.nike.com/");
            List<WebElement> elements = webDriver.findElements(By.xpath("(//ul[@role='menubar']/li)"));

            for (WebElement element: elements ) {
                element = webDriver.findElement(By.xpath("(//ul[@role='menubar']/li)"));
                actions.moveToElement(element).build().perform();
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    /**
    This test will hover over the specific element is the menu bar and opens the link in new tab
     */
    @Test
    public void hover_open_new_tab(){
        try{
            webDriver.get("https://www.nike.com/");
            WebElement element = webDriver.findElement(By.xpath("(//ul[@role='menubar']/li)[6]"));
            actions.moveToElement(element).build().perform();

            WebElement shoes = webDriver.findElement(By.xpath("(//a[contains(@href, '/womens-sale-3yaepz5e1x6')])[1]"));
            shoes.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
            Thread.sleep(2000);

            List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));

            String url = webDriver.getCurrentUrl();

            if (url.equals("https://www.nike.com/w/mens-sale-3yaepznik1")){
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
