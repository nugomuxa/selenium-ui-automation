package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Locators
    private By productsTitle = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");
    private By addToCartFirstItem = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");


    //Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Assertions
    public boolean isProductsTitleDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsTitle)
        ).isDisplayed();
    }

    public int getProductsCount() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems)
        ).size();
    }

    public boolean isCartBadgeDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).isDisplayed();
    }

    // Actions
    public void addFirstProductToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartFirstItem)
        ).click();
    }

    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon)
        ).click();
    }

}
