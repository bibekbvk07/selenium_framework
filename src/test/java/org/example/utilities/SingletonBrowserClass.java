package org.example.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SingletonBrowserClass {
    private static SingletonBrowserClass instance = null;

    private final WebDriver webDriver;
    public static final int TIME_OUTS = 4;

    private SingletonBrowserClass(){
        WebDriverManager.chromedriver().clearDriverCache().driverVersion("").setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(ops);
        webDriver.manage().timeouts().implicitlyWait(TIME_OUTS, TimeUnit.SECONDS);
    }
    public static SingletonBrowserClass getSingleBrowserInstance(){
        if (instance == null){
            instance = new SingletonBrowserClass();
        }else {
            System.out.println("Can only be instantiated once!");
        }
        return instance;
    }
    public WebDriver getWebDriver(){
        return this.webDriver;
    }
}
