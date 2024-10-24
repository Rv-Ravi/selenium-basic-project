package com.learning.selenium.Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    
    public static enum Browsers
    {
        CHROME,
        EDGE,
        FIREFOX
    }

    public static WebDriver driverSetUp(Browsers browser,boolean headless)
    {
        WebDriver driver = null;
        

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOption = new ChromeOptions();
                if(headless)chromeOption.addArguments("--headless");
                driver = new ChromeDriver(chromeOption);
                break;
            case EDGE:
                EdgeOptions EdgeOption = new EdgeOptions();
                if(headless)EdgeOption.addArguments("--headless");
                driver = new EdgeDriver(EdgeOption);
                break;        
            case FIREFOX:
                FirefoxOptions foxOption = new FirefoxOptions();
                if(headless)foxOption.addArguments("--headless");
                driver = new FirefoxDriver(foxOption);
                break;  
            default:
                try {
                    throw new IllegalAccessException("Invalid Browser driver type!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static void delay(int seconds)
    {
        try{
            Thread.sleep(Duration.ofSeconds(seconds));
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
}
