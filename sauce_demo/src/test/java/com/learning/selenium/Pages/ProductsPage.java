package com.learning.selenium.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {


    private WebDriver driver = null;


    @FindBy(xpath = "//div[@class='right_component']//select[@class='product_sort_container']")
    private WebElement sortProductsBy;

    @FindBy(xpath = "//div[@class = 'inventory_item']")
    List<WebElement> inventoryElements;

    public ProductsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToProductPage(String productName)
    {
        List<WebElement>filterProducts = inventoryElements.stream().filter(element -> {
            String name = element.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText();
            return name.contains(productName);
        }).collect(Collectors.toList());

        if (filterProducts.size() > 0)
            filterProducts.get(0).findElement(By.xpath(".//div[@class='inventory_item_label']/a")).click();


    }

    public void addProductToCart(String productName)
    {

        inventoryElements.stream().filter(element -> {
            String name = element.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText();
            return name.contains(productName);
        }).forEach(element -> element.findElement(By.tagName("button")).click());
    }

    public boolean isProductAddedToCart(String productName)
    {
        String xpath = "//div[@class='inventory_item_name ' and text() = '" + productName + "']/parent::a/parent::div/parent::div//button";

        return driver.findElement(By.xpath(xpath)).getText().contains("Remove");
    }
}
