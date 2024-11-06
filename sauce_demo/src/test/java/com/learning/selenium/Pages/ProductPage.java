package com.learning.selenium.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    

    WebDriver driver = null;

    @FindBy(xpath = "//button[contains(@class,'btn_small')]")
    WebElement addToCartBtn;

    @FindBy(id = "back-to-products")
    WebElement backToProds;

    @FindBy(xpath = "//div[@class = 'inventory_details_price']")
    WebElement itemPrice;


    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public float getItemPrice()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        return Float.parseFloat(wait.until(ExpectedConditions.visibilityOf(itemPrice)).getText().substring(1));
    }

    public void addToCart()
    {
        if(!isIteamAddedToCart())
            addToCartBtn.click();
    }

    public boolean isIteamAddedToCart()
    {
        return addToCartBtn.getAttribute("id").equals("remove");
    }

    public void allItemsPage()
    {
        backToProds.click();
    }
}
