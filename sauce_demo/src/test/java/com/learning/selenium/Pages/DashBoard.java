package com.learning.selenium.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoard {


    private WebDriver driver = null;


    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamBurgerMenu;

    @FindAll({@FindBy(xpath = "//nav[@class='bm-item-list']/a[@id='inventory_sidebar_link']"),
                @FindBy(xpath = "//nav[@class='bm-item-list']/a[@id='logout_sidebar_link']")})
    private List<WebElement> menuElement;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private WebElement cartButton;


    public DashBoard(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void logOutAction()
    {
        Actions action = new Actions(driver);
        
        action.moveToElement(hamBurgerMenu).click().build().perform();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(menuElement.get(1)))
                    .click();

        
    }

    public boolean isLogOutSuccess()
    {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/");
    }
}
