package com.learning.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learning.selenium.Pages.DashBoard;
import com.learning.selenium.Pages.LoginPage;
import com.learning.selenium.Pages.ProductPage;
import com.learning.selenium.Pages.ProductsPage;
import com.learning.selenium.Utils.DriverFactory;

public class ProductUnitTest {

    WebDriver driver = null;
    LoginPage loginPage = null;
    ProductsPage itemsPage = null;
    ProductPage itemPage = null;
    DashBoard dashBoard = null;

    private static final String SITE_URL = "https://www.saucedemo.com/";


    @BeforeClass
    public void initSetup()
    {
        driver = DriverFactory.driverSetUp(DriverFactory.Browsers.CHROME, false);
        loginPage = new LoginPage(driver);
        dashBoard = new DashBoard(driver);
        itemsPage = new ProductsPage(driver);
        itemPage = new ProductPage(driver);
    }


    @Test(dataProvider = "loginCreds")
    public void addProductToCart(String username, String password,String prodName)
    {
        driver.get(SITE_URL);
        
        loginPage.loginTask(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessfull());

        itemsPage.goToProductPage(prodName);
        itemPage.addToCart();

        Assert.assertTrue(itemPage.isIteamAddedToCart());

        dashBoard.logOutAction();
        Assert.assertTrue(dashBoard.isLogOutSuccess());


    }


    @DataProvider(name="loginCreds")
    public Object[][] loginCreds()
    {


        return new Object[][] {{"standard_user","secret_sauce","Sauce Labs Bolt T-Shirt"},
                            {"problem_user","secret_sauce","Sauce Labs Bolt T-Shirt"}};

        

    }




    @AfterClass
    public void tearDown()
    {
        if(driver != null)
            driver.quit();
    }
}
