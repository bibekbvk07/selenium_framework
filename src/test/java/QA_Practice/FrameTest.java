package QA_Practice;

import org.example.utilities.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FrameTest {
    SingletonBrowserClass instance;
    @BeforeTest
    public void beforetest(){
        instance = SingletonBrowserClass.getSingleBrowserInstance();
        instance.getWebDriver().get("https://demoqa.com/frames");
    }
    @Test
    public void frame_test(){
        System.out.println(instance.getWebDriver().findElement(By.xpath("//div[@id='framesWrapper']/div[1]")).getText());
        WebElement frame1 = instance.getWebDriver().findElement(By.id("frame1"));
        instance.getWebDriver().switchTo().frame(frame1);
        System.out.println(instance.getWebDriver().findElement(By.id("sampleHeading")).getText());

        // Will take you out of the frame
        instance.getWebDriver().switchTo().defaultContent();
        try{
            System.out.println(instance.getWebDriver().findElement(By.id("sampleHeading")).getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        WebElement frame2 = instance.getWebDriver().findElement(By.id("frame2"));
        instance.getWebDriver().switchTo().frame(frame2);
        System.out.println(instance.getWebDriver().findElement(By.id("sampleHeading")).getText());
    }
    @AfterTest
    public void aftertest(){
        instance.getWebDriver().quit();
    }
}
