package org.example.testngtests;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Mousehovertests {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
       // WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath + "/src/test/resources/driver/chromedriver");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void mousehover() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement addon_element = driver.findElement(By.xpath("//div[text()='Add-ons']"));
        actions.moveToElement(addon_element).build().perform();

        String targetattr =addon_element.getAttribute("target");

        if (targetattr != null && targetattr.equals("_blank")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Link for Add-Ons has no attribute _blank");
        }

        //addon_element.click();
        Thread.sleep(4000);
    }

    @Test
    public void open_in_new_link_test() {
        try {
            driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
            String term = Keys.chord(Keys.COMMAND, Keys.ENTER);
            driver.findElement(By.xpath("//a[text()='Terms of Use']")).sendKeys(term);

            Thread.sleep(1000);


            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            String title = driver.getTitle();

            if (title.equals("Terms of Use")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Landed in wrong tab");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search-strings']")));
            driver.findElement(By.xpath("//input[@id='search-strings']")).sendKeys("bibek");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println();
        }
    }

    @Test
    public void test_new_window() {
        driver.get("https://demoqa.com/browser-windows");
        String currentwindowid = driver.getWindowHandle();

        WebElement new_window = driver.findElement(By.xpath("//button[@id='windowButton']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",new_window);
        new_window.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String allwindow : driver.getWindowHandles()) {
            if (!currentwindowid.contentEquals(allwindow)) {
                driver.switchTo().window(allwindow);
                break;
            }
        }

        String urlofnewwindow = driver.getCurrentUrl();
        System.out.println(urlofnewwindow);
        if (urlofnewwindow.contains("https://demoqa.com/sample")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("URL of child window is different");
        }
    }

    @Test
    public void test_entertext() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.getElementById('user-name').setAttribute('value','new user')");
        WebElement element = driver.findElement(By.xpath("//input[@id='user-name']"));
        js.executeScript("arguments[0].setAttribute('value','test_user_1')", element);

    }

}
