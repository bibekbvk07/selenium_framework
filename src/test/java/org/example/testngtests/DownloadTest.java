package org.example.testngtests;

import org.example.utilities.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DownloadTest {
    WebDriver driver;
    @BeforeTest
    public void beforetest(){
//        WebDriverManager.chromedriver().clearDriverCache().setup();

        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath + "/src/test/resources/webdriver/chromedriver");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator+"downloads");
        chromePrefs.put("download.prompt_for_download", false);

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--disable-extensions");
//        ops.addArguments("--headless=new");
        ops.setExperimentalOption("prefs", chromePrefs);
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    @Test
    public void test_download() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        By download_loc = By.xpath("//a[@id='downloadButton']");
        CommonFunctions.click(download_loc, driver);
    }
    @AfterTest
    public void aftertest(){
        driver.quit();
    }
}
