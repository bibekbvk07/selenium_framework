package org.example.testngtests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import javax.swing.*;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MouseHoverTest {

    WebDriver webDriver;

    @BeforeTest
    public void beforetest(){

        /**
         * This code is using the WebDriverManager library to manage the ChromeDriver executable for Selenium WebDriver
         * This ensures that downloading the correct version of ChromeDriver for the Chrome browser installed on the machine running your Selenium tests.
         */
        WebDriverManager.chromedriver().setup();

        /**
         *  Creating an instance of the 'ChromeOptions' class in Selenium. It allows us to add command line arguments
         *  that will be passed to the Chrome browser when it starts. It is useful for configuring various behaviours and settings of
         *  the Chrome browser.
         */
        ChromeOptions ops = new ChromeOptions();

        /**
         The --remote-allow-origins Chrome command-line switch is used to specify the origins that are allowed to connect to Chrome Remote Debugger.
         When you add ops.addArguments("--remote-allow-origins=*");, you are configuring Chrome to allow connections from any origin.
         Keep in mind that using wildcard (*) to allow any origin might have security implications, so it should be used carefully,
         especially in production environments. Always consider the security requirements and restrict origins appropriately based on your needs.
         */
        ops.addArguments("--remote-allow-origins=*");

        /**
         * In this example, the addArguments("--start-maximized") line adds the --start-maximized command-line argument to the ChromeOptions,
         * and when you launch the ChromeDriver with these options, the browser window will open in a maximized state
         */
        ops.addArguments("--start-maximized");

        ops.addArguments("--incognito"); // Chrome browser will run in incognito mode

        /**
         Command-line option in Chrome is used to disable the browser's geolocation feature.
         Geolocation allows websites to request information about the user's physical location.
         By adding ops.addArguments("--disable-geolocation"); to your ChromeOptions in Selenium,
         you are instructing the browser to prevent websites from accessing the user's geolocation information.
         */
        ops.addArguments("--disable-geolocation");

        /**
         The setExperimentalOption method in Selenium's ChromeOptions class is used to set experimental options for Chrome.
         In the case of ops.setExperimentalOption("useAutomationExtension", false);, it is used to disable the Chrome automation extension.
         By setting the "useAutomationExtension" to false, you are instructing Chrome not to load the ChromeDriver automation extension.
         This extension is typically loaded by default to facilitate communication between the Chrome browser and the Selenium WebDriver.
         */
        ops.setExperimentalOption("useAutomationExtension", false);

        /**
         The setExperimentalOption method in Selenium's ChromeOptions class is used to set experimental options for Chrome.
         In the case of ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));,
         it is used to exclude the "enable-automation" Chrome switch.
         By excluding the "enable-automation" switch, you are attempting to prevent websites from detecting that they are being automated by Selenium.
         Some websites use this switch to check whether the browser is being controlled by WebDriver, and by excluding it,
         you may reduce the chances of being detected as an automated script.
         */
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        /**
         webDriver object is an instance of the ChromeDriver class, configured with the specified options.
         You can use this webDriver object to interact with the Chrome browser in your Selenium tests.
         */
        webDriver = new ChromeDriver(ops);

        /**
         The line webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); is setting the implicit wait timeout for the WebDriver.
         The implicit wait is a global setting that tells the WebDriver to wait for a certain amount of time before throwing a
         NoSuchElementException or a similar exception when trying to find an element.
         --> The timeout is set to 4 seconds. This means that if the WebDriver cannot find an element immediately,
         it will wait up to 4 seconds for the element to become available. If the element is found before the timeout expires,
         the WebDriver proceeds with the next step. If the element is not found within the specified time, a NoSuchElementException will be thrown.

         --> Using implicit waits can be helpful in handling dynamic elements or scenarios where the web page takes some time to load.
         However, it's essential to use them judiciously, as setting a too long implicit wait can introduce unnecessary delays in your test execution.

         --> It's also worth noting that the implicit wait is applied globally to all findElement calls in your test.

         --> TimeUnit.SECONDS is an enumeration in Java that represents the unit of time in seconds.
         It is part of the java.util.concurrent package and is commonly used when dealing with timeouts or durations
         in multithreading and concurrent programming.

         */
        webDriver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
    }

    @Test
    public void hoverTest(){
        try {
            webDriver.get("https://www.spicejet.com/");
            /**
             This line creates an instance of the Actions class, which is used to perform advanced user interactions,
             like mouse movements and keyboard actions.
             */
            Actions actions = new Actions(webDriver);
            //This line locates the web element representing the "SpiceClub" element on the SpiceJet website using an XPath expression
            WebElement spicejet = webDriver.findElement(By.xpath("(//div[text()='SpiceClub'])[1]"));
            // System.out.println(spicejet);

            //This line moves the mouse cursor to the "SpiceClub" element, simulating a mouse hover action.
            actions.moveToElement(spicejet).build().perform();

            /**
             The line WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); is creating an instance of the WebDriverWait class in Selenium.
             The WebDriverWait class is part of the org.openqa.selenium.support.ui package and is used for implementing explicit waits in Selenium WebDriver.
             In this example, the until method is used with an expected condition (ExpectedConditions.presenceOfElementLocated) to wait for up to 10 seconds
             until the specified condition is met. If the condition is met within the specified time, the method returns the found element (WebElement).
             If the condition is not met within the timeout, a TimeoutException is thrown.

             Using explicit waits with WebDriverWait is a good practice in Selenium to handle dynamic elements or scenarios where you need to wait
             for certain conditions to be satisfied before proceeding with your test.
             */
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            /**
             After creating the WebDriverWait instance, you can use it to wait for a specific condition (such as the presence of an element,
             visibility of an element, etc.) to be true before proceeding with the next steps in your test. Here's an example
             */
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/home#program')]")));

            /**
              By.xpath("//a[contains(@href,'/home#program')]"): This is an XPath expression used as the locator strategy.
              It finds an anchor (<a>) element that contains the specified substring in its href attribute. Here's the breakdown of the XPath:
             --> //a: Selects any anchor (<a>) element in the document.
             --> contains(@href, '/home#program'): Specifies a condition that the href attribute of the anchor element must contain the substring '/home#program'.
             * The located WebElement is then assigned to the variable homeProgram.
             * You can now interact with this element in your test, such as clicking on it, retrieving text, checking visibility, etc.
             */
            WebElement homeProgram = webDriver.findElement(By.xpath("//a[contains(@href,'/home#program')]"));

            /**
             --> ## Here's the breakdown:
             * homeProgram: This is the WebElement that was located earlier using Selenium. It represents an anchor (<a>) element on the web page.
             * .getAttribute("target"): This method is used to retrieve the value of a specified attribute of the WebElement.
             * In this case, it's retrieving the value of the "target" attribute.
             * String targetAttribute: The retrieved attribute value is stored in a String variable named targetAttribute.
             */
            String targetAttribute = homeProgram.getAttribute("target");
            System.out.println(targetAttribute);

            if (targetAttribute != null && targetAttribute.equals("_blank")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Link Our Program has no attribute _blank");
            }
            homeProgram.click();
            Thread.sleep(4000);
             //wait.until(ExpectedConditions.elementToBeClickable(homeProgram));

            /**
             --->    Here's a breakdown of each part:
             actions: This is an instance of the Actions class, which is part of the org.openqa.selenium.interactions package in Selenium.
             The Actions class provides methods to perform complex user interactions, such as key presses, mouse movements, and more.
             --> keyDown(Keys.COMMAND): This method simulates pressing down the "Command" key (on macOS) or "Ctrl" key (on Windows).
             --> keyDown(Keys.TAB): This method simulates pressing down the "Tab" key.
             --> build(): This method is used to compile all the actions into a single composite action.
             --> perform(): This method is used to execute the composite action.
             Note: --> For example, you might use this code to switch between browser tabs in a web application.
             */
            actions.keyDown(Keys.COMMAND).keyDown(Keys.TAB).build().perform();

        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
        }
    }

    @Test
    public void hoverTestAddOns(){
        try{
            webDriver.get("https://www.spicejet.com/");
            Actions actions = new Actions(webDriver);

            WebElement addOns = webDriver.findElement(By.xpath("//div[text()='Add-ons']"));
            actions.moveToElement(addOns).build().perform();

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/YouFirst.aspx')]")));

            WebElement youFirst = webDriver.findElement(By.xpath("//a[contains(@href,'/YouFirst.aspx')]"));

            String targetAttr = youFirst.getAttribute("target");
            System.out.println(targetAttr);

            // validation
            if (targetAttr != null && targetAttr.equals("_blank")){
                Assert.assertTrue(true);
            }else{
                Assert.fail("Link to You1st has no attribute _blank");
            }
            youFirst.click();
            Thread.sleep(4000);
            actions.keyDown(Keys.COMMAND).keyDown(Keys.TAB).build().perform();

        }  catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
        }
    }
    @Test
    public void test_new_window() {
        webDriver.get("https://demoqa.com/browser-windows");
        String currentwindowid = webDriver.getWindowHandle();
        webDriver.findElement(By.xpath("//button[@id='windowButton']")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String allwindow : webDriver.getWindowHandles()) {
            if (!currentwindowid.contentEquals(allwindow)) {
                webDriver.switchTo().window(allwindow);
                break;
            }
        }

        String urlofnewwindow = webDriver.getCurrentUrl();
        if (urlofnewwindow.contains("https://demoqa.com/sample")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("URL of child window is different");
        }

    }

}


