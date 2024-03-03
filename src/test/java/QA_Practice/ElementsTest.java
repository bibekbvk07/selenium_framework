package QA_Practice;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ElementsTest {
    WebDriver webDriver;
    @BeforeTest
    public void beforeTest(){
        CommonFunctions.invoke();
        webDriver = new ChromeDriver(CommonFunctions.getChromeOptions());
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    /**
     This test method, textBoxTest, is designed to validate the functionality of the text box feature on the
     specified webpage "https://demoqa.com/text-box". The test inputs sample data into various text fields,
     including username, email, current address, and permanent address. It then submits the form and checks
     for any exceptions during the execution.
     @Test_Steps:

     Navigate to the webpage "https://demoqa.com/text-box".
     Enter "Hello World" into the username input field.
     Enter "hello@gmail.com" into the email input field.
     Enter "123 Warren St \nMankato, MN, 56001" into both current and permanent address text areas.
     Click the submit button.
     @throws Exception If any unexpected issues occur during the execution of the test, an exception message
     Note: This test is meant to be a basic validation of the text box functionality and does not include
     in-depth validations or assertions. For a more comprehensive test suite, additional scenarios and assertions
     can be added.
     */
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

    @Test
    public void orm_new_tab(){
        try{
            webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Actions actions = new Actions(webDriver);
            webDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
            webDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
            webDriver.findElement(By.xpath("//button[@type='submit']")).click();

            WebElement myInfo = webDriver.findElement(By.xpath("//a[contains(@href, '/viewMyDetails')]"));
            actions.moveToElement(myInfo).build().perform();
            Thread.sleep(2000);
            myInfo.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
//            myInfo.click();
            Thread.sleep(4000);

            Set<String> windowHandles = webDriver.getWindowHandles();
            Iterator<String> iterator = windowHandles.iterator();
            String parentHandle = iterator.next();
            String childHandle = iterator.next();
            webDriver.switchTo().window(childHandle);

            System.out.println("Parent Handle: "+ parentHandle);
            System.out.println("Child Handle: "+ childHandle);
            WebElement element = webDriver.findElement(By.xpath("//input[@placeholder='First Name']"));

            element.click();
            element.clear();
            element.sendKeys("Bibek");
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
