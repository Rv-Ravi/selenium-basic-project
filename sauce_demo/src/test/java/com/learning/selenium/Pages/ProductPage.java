package com.learning.selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    

    WebDriver driver = null;

    @FindBy(xpath = "//button[contains(@class,'btn_small')]")
    WebElement addToCartBtn;

    @FindBy(id = "back-to-products")
    WebElement backToProds;

    @FindBy(xpath = "//div[class = 'inventory_details_price']")
    WebElement itemPrice;


    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getItemPrice()
    {
        return itemPrice.getText();
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
