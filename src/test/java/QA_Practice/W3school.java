package QA_Practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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

public class W3school {
    WebDriver webDriver;

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(ops);
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        webDriver.get("https://www.w3schools.com/");
    }

    /**
     This test checks the web Title
     */
    @Test
    public void checkTitle(){
        String title = webDriver.getTitle();
        System.out.println("The title is: "+ title);
        if (title.equals("W3Schools Online Web Tutorials")){
            Assert.assertTrue(true);
        }else{
            Assert.fail("Incorrect Title");
        }
    }
    @Test
    public void searchInputTest(){
        try{
            WebElement searchBar = webDriver.findElement(By.id("tnb-google-search-input"));
            searchBar.click();
            searchBar.sendKeys("JAVA");
            webDriver.findElement(By.xpath("(//a[contains(@href, '/java/default.asp')])[1]")).click();

            String webTitle = webDriver.getTitle();
            System.out.println("The title is: "+ webTitle);

            if (webTitle.equals("Java Tutorial")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Incorrect Web page");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Test
    public void mouseHoverTest(){
        try{
            Actions actions = new Actions(webDriver);
            List<WebElement> elements = webDriver.findElements(By.xpath("//div[@id= 'subtopnav']/a"));

            List<String> topics = new ArrayList<>();
            WebElement element;
            // Iterate the hover effect on first 6 elements
            for (int i = 1; i <=10; i++){
                // will iterate till index 6 and stores the element to Web element
                element = webDriver.findElement(By.xpath("(//div[@id= 'subtopnav']/a)["+i+"]"));
                // Move to the elements to trigger the hover effect
                actions.moveToElement(element).build().perform();
                // Wait for a short time to see the hover effect
                Thread.sleep(1000);
                topics.add(element.getText());

                // validation
                if (element.getText().equals("JAVA")){
                    element.click();
                    break;
                }
            }
            for (String eachTopic: topics) {
                System.out.print(eachTopic + " ");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void mouseHoverTest1(){
        try{
            Actions actions = new Actions(webDriver);
            WebElement element = webDriver.findElement(By.id("navbtn_exercises"));
            actions.moveToElement(element).build().perform();
            Thread.sleep(1000);
            element.click();
            webDriver.findElement(By.xpath("//div[@data-name='java']//a[normalize-space(text())='Exercise']")).click();
            String currentWindowID = webDriver.getWindowHandle();
            WebElement newWindow = webDriver.findElement(By.xpath("//a[text()='Start Java Exercises ❯']"));
            newWindow.click();

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            // Waits for new tab to open
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String windowId : webDriver.getWindowHandles()) {
                if (!currentWindowID.equals(windowId)){
                    webDriver.switchTo().window(windowId);
                    break;
                }
            }
            String urlOfNewWindow = webDriver.getCurrentUrl();
            System.out.println(urlOfNewWindow);

            if (urlOfNewWindow.equals("https://www.w3schools.com/java/exercise.asp?filename=exercise_syntax1")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Different URL");
            }
//            String [] ans = new String[]{"System", "out","println"};
            String [] ans = {"System", "out","println"};
            WebElement value;
            for (int i = 1; i <= 3; i++){
                value = webDriver.findElement(By.xpath("(//input[@class='editablesection'])["+i+"]"));
                value.sendKeys(ans[i-1]);
                Thread.sleep(1000);
            }
            webDriver.findElement(By.xpath("//button[text()='Submit Answer ❯']")).click();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Test
    public void scrollTest(){
        try{
            Actions actions = new Actions(webDriver);
            WebElement element = webDriver.findElement(By.xpath("//h2[text()='How To Section']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", element);
            Thread.sleep(3000);

        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @AfterTest
    public void aftertest(){
        webDriver.quit();
    }
}
