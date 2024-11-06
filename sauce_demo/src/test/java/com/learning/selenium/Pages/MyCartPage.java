package com.learning.selenium.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {



    WebDriver driver = null;

    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    WebElement contnueBtn;

    @FindBy(id = "checkout")
    WebElement checkotBtn;

    public MyCartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void removeElementFromCart(String ProductName)
    {
        checkItemInCart(ProductName).findElement(By.xpath(".//div[@class='item_pricebar']/button")).click();
    }

    public WebElement checkItemInCart(String ProductName)
    {
        List<WebElement> elements = cartItems.stream().filter(element -> {
            String name = element.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText().trim();
            return name.equals(ProductName);
        }).collect(Collectors.toList());

        return elements.size() > 0 ? elements.get(0) : null;
    }

    public void continueShopping(){
        contnueBtn.click();
    }

    public void CheckOut()
    {
        if(cartItems.size() > 0)
            checkotBtn.click();
    }

}
