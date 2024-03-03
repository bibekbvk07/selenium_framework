package QA_Practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

public class W3school {
    WebDriver webDriver;
    Actions actions;

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
        actions = new Actions(webDriver);
        webDriver.get("https://www.w3schools.com/");
    }

    /**
     This test checks the web Title
     */
    @Test
    public void checkTitle(){
        try{
            String title = webDriver.getTitle();
            System.out.println("The title is: "+ title);
            if (title.equals("W3Schools Online Web Tutorials")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Incorrect Title");
            }
        }catch(Exception e){
            e.getStackTrace();
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
    public void new_tab_test(){
        try{
            WebElement java = webDriver.findElement(By.xpath("//div[@id='subtopnav']/a[6]"));
            actions.moveToElement(java).build().perform();
            Thread.sleep(500);
            java.click();

            String javaWindowId = webDriver.getWindowHandle();
            System.out.println(javaWindowId);

            WebElement scroll = webDriver.findElement(By.xpath("//div[@id='leftmenuinnerinner']/h2[text()='Java Methods']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", scroll);

            WebElement javaMethods = webDriver.findElement(By.xpath("//a[text()='Java Methods']"));
            javaMethods.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
            Thread.sleep(500);

            List<String> handles = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(handles.get(1));
            Thread.sleep(3000);

            String javaMethodsId = webDriver.getWindowHandle();
            System.out.println(javaMethodsId);

            String url = webDriver.getCurrentUrl();
            if (url.equals("https://www.w3schools.com/java/java_methods.asp")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Wrong tab");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void mouseHoverAndNewTabTest(){
        try{
            WebElement element = webDriver.findElement(By.id("navbtn_exercises"));
            actions.moveToElement(element).build().perform();
            Thread.sleep(1000);
            element.click();

            webDriver.findElement(By.xpath("//div[@data-name='java']//a[normalize-space(text())='Exercise']")).click();
            String currentWindowID = webDriver.getWindowHandle();
            System.out.println(currentWindowID);
            WebElement newWindow = webDriver.findElement(By.xpath("//a[text()='Start Java Exercises ❯']"));
            newWindow.click();

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            // Waits for new tab to open
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String windowId : webDriver.getWindowHandles()) {
                if (!currentWindowID.equals(windowId)){
                    webDriver.switchTo().window(windowId);
                    System.out.println(windowId);
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
    public void switch_between_tabs(){
        try{
            WebElement java = webDriver.findElement(By.xpath("//div[@id='subtopnav']/a[6]"));
            actions.moveToElement(java).build().perform();
            Thread.sleep(500); // wait to see the hover effect
            java.click();

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", webDriver.findElement(By.xpath("//h2[text()='Java Methods']")));
            Thread.sleep(500);

            WebElement javaMethods = webDriver.findElement(By.xpath("//a[text()='Java Methods']"));
            WebElement javaMethodParam = webDriver.findElement(By.xpath("//a[text()='Java Method Parameters']"));
            javaMethods.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
            javaMethodParam.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));

            /**
             Set<String> windowHandles = webDriver.getWindowHandles();
             System.out.println(windowHandles);

             Iterator<String> handle = windowHandles.iterator();
             String parentWindow = handle.next();
             String child1 = handle.next();
             String child3 = handle.next();

             //switching the window handles
             webDriver.switchTo().window(child3);
             System.out.println(webDriver.getWindowHandle());
             Thread.sleep(2000);
             webDriver.switchTo().window(parentWindow);
             System.out.println(webDriver.getWindowHandle());
             */

            List<String> windowHandles = new ArrayList<>(webDriver.getWindowHandles());
            System.out.println(windowHandles);
            for (String handle: windowHandles) {
                webDriver.switchTo().window(handle);
                Thread.sleep(2000);
                System.out.println(webDriver.getWindowHandle());
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    @Test
    public void scrollTest(){
        try{
            WebElement element = webDriver.findElement(By.xpath("//h2[text()='How To Section']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", element);
            Thread.sleep(1000);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @AfterTest
    public void aftertest(){
        webDriver.quit();
    }
}
