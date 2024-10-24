package com.learning.selenium.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


    private WebDriver driver;

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement userName;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void loginTask(String user,String pass)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(userName));

        userName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }

    public boolean isLoginSuccessfull()
    {
        String url = driver.getCurrentUrl();

        return url.contains("inventory.html");
    }

    public String loginErrorCheck()
    {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        String errorMsg = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(
                                        By.xpath("//h3[@data-test='error']"))
                                        ).getText().split(":")[1].trim();

        driver.findElement(By.xpath("//button[@data-test='error-button']")).click();

        return errorMsg;

    }

    public String getUserNameTyped()
    {
        return userName.getAttribute("value");
    }

}
