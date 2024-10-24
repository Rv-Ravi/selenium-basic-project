package com.learning.selenium;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.learning.selenium.Pages.DashBoard;
import com.learning.selenium.Pages.LoginPage;
import com.learning.selenium.Utils.DriverFactory;

public class LoginUnitTest 
{

    private LoginPage loginPage;
    private DashBoard dashBoardOptions;

    private WebDriver driver = null;
    private static final String SITE_URL = "https://www.saucedemo.com/";

    @BeforeClass
    public void initSetup()
    {
        driver = DriverFactory.driverSetUp(DriverFactory.Browsers.CHROME, false);
        loginPage = new LoginPage(driver);
        dashBoardOptions = new DashBoard(driver);
    }


    @Test(dataProvider = "loginCreds")
    public void loginWithValidCreds(String username, String password)
    {
        driver.get(SITE_URL);
        
        loginPage.loginTask(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessfull());


    }

    @Test(dependsOnMethods = "loginWithValidCreds",alwaysRun = true)
    public void logOut()
    {
        dashBoardOptions.logOutAction();
        Assert.assertTrue(dashBoardOptions.isLogOutSuccess());
    }

    @Test(dependsOnMethods = "logOut")
    public void loginWithInvalidCreds()
    {
        driver.get(SITE_URL);

        loginPage.loginTask("standard_user1", "secret_sauce");
        Assert.assertEquals(loginPage.loginErrorCheck(), "Username and password do not match any user in this service");
        Assert.assertEquals(loginPage.getUserNameTyped(),"standard_user1");

    }

    @Test(dependsOnMethods = "loginWithInvalidCreds")
    public void lockedUserLogin()
    {
        driver.get(SITE_URL);

        loginPage.loginTask("locked_out_user", "secret_sauce");
        Assert.assertEquals(loginPage.loginErrorCheck(), "Sorry, this user has been locked out.");
        Assert.assertEquals(loginPage.getUserNameTyped(),"locked_out_user");
    }

    @DataProvider(name="loginCreds")
    public Object[][] loginCreds()
    {


        return new Object[][] {{"standard_user","secret_sauce"},
                                {"visual_user","secret_sauce"},
                            {"problem_user","secret_sauce"}};

        

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
