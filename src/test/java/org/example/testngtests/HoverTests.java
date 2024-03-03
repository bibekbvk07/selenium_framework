package org.example.testngtests;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HoverTests {
    WebDriver webDriver;
    @BeforeTest
    public void beforetest(){
        CommonFunctions.invoke();
        webDriver = new ChromeDriver(CommonFunctions.getChromeOptions());
        //implicit wait
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void hoverTest1(){
        try{
            // Navigate to the webpage
            webDriver.get("https://the-internet.herokuapp.com/");
            webDriver.findElement(By.xpath("//a[contains(@href, '/hovers')]")).click();

            Actions actions = new Actions(webDriver);

            // Locate the parent div with class "example"
//            WebElement exampleDiv = webDriver.findElement(By.className("example"));

            // Collection of Web-elements
            List<WebElement> elementsList = webDriver.findElements(By.className("figure"));

            // Iterate through each figure and perform hover action
            for(WebElement figure : elementsList){
                // Move to the figure to trigger the hover effect
                actions.moveToElement(figure).build().perform();
                // Wait for a short time to see the hover effect
                Thread.sleep(1000);

                // Extract information or perform any actions you need during the hover
                WebElement nameUser = figure.findElement(By.tagName("h5"));
                System.out.println(nameUser.getText());

                WebElement profileLink = figure.findElement(By.tagName("a"));
                System.out.println(profileLink.getAttribute("href"));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // close the browser
        webDriver.quit();
    }

    @Test(priority = 2)
    public void hoverTest2(){
        try {
            webDriver.get("https://www.spicejet.com/");
            Actions actions = new Actions(webDriver);

            WebElement addOns = webDriver.findElement(By.xpath("//div[text()='Add-ons']"));
            actions.moveToElement(addOns).build().perform(); // Trigger the hover effect on AddOns

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/YouFirst.aspx')]")));

            WebElement youFirst = webDriver.findElement(By.xpath("//a[contains(@href, '/YouFirst.aspx')]"));

            // Stores the target Attribute as a String
            String targetAttribute = youFirst.getAttribute("target");
            System.out.println(targetAttribute);

            // Validation of target attribute
            if (targetAttribute != null && targetAttribute.equals("_blank")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("You1st link has no target attribute! ");
            }
            youFirst.click();
            Thread.sleep(4000);
            actions.keyDown(Keys.COMMAND).keyDown(Keys.TAB).build().perform();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // Close the browser
        webDriver.quit();
    }
    @Test
    public void open_in_new_link_test(){
        try{
            webDriver.get("https://www.tutorialspoint.com/about/about_careers.htm");
            WebElement element = webDriver.findElement(By.xpath("//*[text()='Terms of Use']"));
            String keyBoardAction = Keys.chord(Keys.COMMAND, Keys.ENTER);
            element.sendKeys(keyBoardAction);
            Thread.sleep(1000);

            List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));

            String title = webDriver.getTitle();

            if (title.equals("Terms of Use")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Wrong tab");
            }
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-strings")));
            webDriver.findElement(By.id("search-strings")).sendKeys("Bibek");

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println();
        }
        // Close the browser
        webDriver.quit();
    }

    @Test(priority = 4)
    public void test_new_window() {
        try {
            webDriver.get("https://demoqa.com/browser-windows");
            String currentwindowid = webDriver.getWindowHandle();

            WebElement new_window = webDriver.findElement(By.xpath("//button[@id='windowButton']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", new_window);
            new_window.click();

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String allwindow : webDriver.getWindowHandles()) {
                if (!currentwindowid.contentEquals(allwindow)) {
                    webDriver.switchTo().window(allwindow);
                    break;
                }
            }

            String urlofnewwindow = webDriver.getCurrentUrl();
            System.out.println(urlofnewwindow);
            if (urlofnewwindow.contains("https://demoqa.com/sample")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("URL of child window is different");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Close the browser
        webDriver.quit();
    }

    @Test
    public void test_alerts_only_accept() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");

        CommonFunctions.click(By.id("alertButton"), webDriver);

        Thread.sleep(2000);
        System.out.println(webDriver.switchTo().alert().getText());

        webDriver.switchTo().alert().accept();
    }

    @Test
    public void test_alerts_confirmation() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");

        CommonFunctions.click(By.id("confirmButton"), webDriver);

        Thread.sleep(1000);
        System.out.println(webDriver.switchTo().alert().getText());

        webDriver.switchTo().alert().dismiss();
    }

    @Test
    public void test_alerts_text() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");

        CommonFunctions.click(By.id("promtButton"), webDriver);

        Thread.sleep(1000);
        Alert alert = webDriver.switchTo().alert();
        System.out.println(alert.getText());
        Thread.sleep(1000);
        alert.sendKeys("Hi there");
        alert.accept();
    }

    @Test
    public void selectTest(){
        try{
            webDriver.get("https://demoqa.com/select-menu");
            WebElement selector = webDriver.findElement(By.xpath("//select[@id='oldSelectMenu']"));

            Select select = new Select(selector);
            select.selectByIndex(1);
            select.selectByValue("Purple");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_sliders() {
        try{
            webDriver.get("https://demoqa.com/slider");
            Actions actions = new Actions(webDriver);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement slider = webDriver.findElement(By.xpath("//input[contains(@class,'range-slider')]"));
            wait.until(ExpectedConditions.elementToBeClickable(slider));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);",slider);
            actions.clickAndHold(slider).build().perform();
            Thread.sleep(1000);
            slider.click();

            int currentSlideValue = Integer.parseInt(slider.getAttribute("value"));
            System.out.println("Current Value: "+ currentSlideValue);
            int destinationValue = 35;

            if (currentSlideValue > destinationValue){
                for (int i = currentSlideValue; i > destinationValue; i--) {
                    actions.sendKeys(Keys.ARROW_LEFT).build().perform();
                }
            }else {
                for (int i = currentSlideValue; i < destinationValue; i++) {
                    actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        webDriver.quit();
    }

    @Test
    public void dragAndDrop(){
        try {
            webDriver.get("https://demoqa.com/droppable");
            Actions actions = new Actions(webDriver);

            WebElement drag = webDriver.findElement(By.xpath("//div[@id='draggable']"));

            WebElement drop = webDriver.findElement(By.xpath("//div[@id='draggable']/following-sibling::div"));

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", drag);
            //actions.clickAndHold(drag).moveToElement(drop).release(drop).build().perform();

            actions.dragAndDrop(drag, drop).perform();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

