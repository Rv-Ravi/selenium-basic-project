package com.learning.selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learning.selenium.Pages.DashBoard;
import com.learning.selenium.Pages.LoginPage;
import com.learning.selenium.Pages.MyCartPage;
import com.learning.selenium.Pages.ProductPage;
import com.learning.selenium.Pages.ProductsPage;
import com.learning.selenium.Utils.DriverFactory;

public class ProductUnitTest {

    WebDriver driver = null;
    LoginPage loginPage = null;
    ProductsPage itemsPage = null;
    ProductPage itemPage = null;
    DashBoard dashBoard = null;
    MyCartPage cartPage = null;

    private static final String SITE_URL = "https://www.saucedemo.com/";


    @BeforeClass
    public void initSetup()
    {
        driver = DriverFactory.driverSetUp(DriverFactory.Browsers.CHROME, false);
        loginPage = new LoginPage(driver);
        dashBoard = new DashBoard(driver);
        itemsPage = new ProductsPage(driver);
        itemPage = new ProductPage(driver);
        cartPage = new MyCartPage(driver);
    }


    @Test(dataProvider = "loginCreds")
    public void addProductToCart(String username, String password, String[] details)
    {
        driver.get(SITE_URL);
        
        loginPage.loginTask(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessfull());

        itemsPage.goToProductPage(details[0]);
        itemPage.addToCart();

        Assert.assertTrue(itemPage.isIteamAddedToCart());

        dashBoard.logOutAction();
        Assert.assertTrue(dashBoard.isLogOutSuccess());
    }

    @Test(dataProvider = "loginCreds")
    public void addProductsToCart(String username, String password, String[] details)
    {
        driver.get(SITE_URL);
        
        loginPage.loginTask(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessfull());

        for(String item : details)
        {
            itemsPage.goToProductPage(item);
            itemPage.addToCart();
            Assert.assertTrue(itemPage.isIteamAddedToCart());
            itemPage.allItemsPage();
        }

        dashBoard.logOutAction();
        Assert.assertTrue(dashBoard.isLogOutSuccess());
    }

    @Test(dataProvider = "loginCreds")
    public void validateCartPage(String username, String password, String[] details)
    {
        driver.get(SITE_URL);
        
        loginPage.loginTask(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessfull());

        float priceSum = 0.0f;

        for(String item : details)
        {
            itemsPage.goToProductPage(item);
            itemPage.addToCart();
            Assert.assertTrue(itemPage.isIteamAddedToCart());
            priceSum += itemPage.getItemPrice();
            itemPage.allItemsPage();
        }

        Assert.assertEquals(Math.round(priceSum * 100.0f) / 100.0f, 95.97f);
        dashBoard.goToCart();

        for(String item : details)
        {
            Assert.assertNotNull(cartPage.checkItemInCart(item));
        }

        cartPage.continueShopping();
        dashBoard.logOutAction();
        Assert.assertTrue(dashBoard.isLogOutSuccess());
    }

    @DataProvider(name="loginCreds")
    public Object[][] loginCreds()
    {


        return new Object[][] {{"standard_user","secret_sauce","Sauce Labs Bolt T-Shirt","Sauce Labs Backpack",
                                "Sauce Labs Fleece Jacket"}};

        

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
            driver.quit();
    }
}
