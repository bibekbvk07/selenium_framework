package QA_Practice;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ElementsTest {
    WebDriver webDriver;
    @BeforeTest
    public void beforeTest(){
        CommonFunctions.invoke();
        webDriver = new ChromeDriver(CommonFunctions.getChromeOptions());
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    @Test
    public void textBoxTest(){
        try{
            webDriver.get("https://demoqa.com/text-box");

            webDriver.findElement(By.xpath("//input[@id='userName']")).sendKeys("Hello World");
            webDriver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("hello@gmail.com");
            webDriver.findElement(By.xpath("//textarea[@id='currentAddress']")).sendKeys("123 Warren St \n"+"Mankato, MN, 56001");
            webDriver.findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys("123 Warren St \n"+"Mankato, MN, 56001");
            webDriver.findElement(By.id("submit")).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkBoxTest(){
        try{
            webDriver.get("https://demoqa.com/checkbox");

//            webDriver.findElement(By.xpath("//div[@class='rct-options']//button[@title='Expand all']")).click();
            webDriver.findElement(By.xpath("(//span[@class='rct-text']//button)[1]")).click();

            WebElement checkbox;

            for (int i = 1; i <=3; i++) {
                checkbox = webDriver.findElement(By.xpath("//li/ol/li["+i+"]//span[@class='rct-checkbox']"));
                checkbox.click();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void radioButtonTest(){
        try{
            webDriver.get("https://demoqa.com/radio-button");
            WebElement yesRadio = webDriver.findElement(By.cssSelector("label[for='yesRadio']"));
            WebElement impressiveRadio = webDriver.findElement(By.cssSelector("label[for='impressiveRadio']"));
            WebElement noRadio = webDriver.findElement(By.cssSelector("label[for='noRadio']"));

            Thread.sleep(3000);
            yesRadio.click();

            if (yesRadio.isSelected() || !impressiveRadio.isSelected()){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Both selected");
            }

            noRadio.click();

            if (noRadio.isSelected()){
                Assert.fail("No Radio button selected");
            }else{
                Assert.assertTrue(true);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void webTableTest(){
        try{
            webDriver.get("https://demoqa.com/webtables");

            // deleting records from the web tables
            webDriver.findElement(By.xpath("//span[@id='delete-record-1']")).click();

            //editing records from the web table
            webDriver.findElement(By.xpath("//span[@id='edit-record-2']")).click();
            webDriver.findElement(By.id("department")).clear();
            webDriver.findElement(By.id("department")).sendKeys("IT Department");
            webDriver.findElement(By.xpath("//button[@id='submit']")).click();

            // adding records in the web tables
            webDriver.findElement(By.id("addNewRecordButton")).click();

            webDriver.findElement(By.id("firstName")).sendKeys("Carrie");
            webDriver.findElement(By.id("lastName")).sendKeys("Bishnoi");
            webDriver.findElement(By.id("userEmail")).sendKeys("carrie.bishnoi@gmail.com");
            webDriver.findElement(By.id("age")).sendKeys("45");
            webDriver.findElement(By.id("salary")).sendKeys("45000");
            webDriver.findElement(By.id("department")).sendKeys("Health");
            webDriver.findElement(By.xpath("//button[@id='submit']")).click();

            // search records
            webDriver.findElement(By.id("searchBox")).click();
            webDriver.findElement(By.id("searchBox")).sendKeys("Carrie");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void scrollTest(){
        try{
            webDriver.get("https://www.tutorialspoint.com/about/about_careers.htm");
            WebElement element = webDriver.findElement(By.xpath("//h2[text()='How to Apply?']"));
            CommonFunctions.scrollToElements(webDriver, element);
            WebElement button = webDriver.findElement(By.xpath("//a[contains(@href, '/about/about_privacy.htm')]/button"));
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/about/about_privacy.htm')]/button")));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            Thread.sleep(3000);
            button.click();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    @AfterTest
    public void aftertest(){
        webDriver.quit();
    }
}
