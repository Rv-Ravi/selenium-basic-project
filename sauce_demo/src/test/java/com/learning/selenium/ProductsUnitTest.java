package com.learning.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.learning.selenium.Pages.DashBoard;
import com.learning.selenium.Pages.LoginPage;
import com.learning.selenium.Pages.ProductsPage;
import com.learning.selenium.Utils.DriverFactory;

public class ProductsUnitTest {

    private static final String SITE = "https://www.saucedemo.com/";

    private WebDriver driver;

     private LoginPage loginPage;
     private DashBoard dashBoard;
     private ProductsPage productsPage;

     @BeforeClass
     public void initSetup()
     {
        driver = DriverFactory.driverSetUp(DriverFactory.Browsers.CHROME, false);
        loginPage = new LoginPage(driver);
        dashBoard = new DashBoard(driver);
        productsPage = new ProductsPage(driver);
     }

     @Test
     public void productAddToCart()
     {
        driver.get(SITE);

        loginPage.loginTask("standard_user","secret_sauce");
        Assert.assertTrue(loginPage.isLoginSuccessfull());

        productsPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        Assert.assertTrue(productsPage.isProductAddedToCart("Sauce Labs Bolt T-Shirt"));

        dashBoard.logOutAction();

        Assert.assertTrue(dashBoard.isLogOutSuccess());
     }

     private void delay(int seconds)
     {
        try{
            Thread.sleep(Duration.ofSeconds(seconds));
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
     }

     @AfterClass
     public void tearDown()
     {
        if(driver != null)
        {
            driver.quit();
            driver = null;
        }
     }
}
