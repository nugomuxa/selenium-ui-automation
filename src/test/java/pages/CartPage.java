package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Locators
    private By cartTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By firstItemName = By.className("inventory_item_name");
    private By firstItemPrice = By.className("inventory_item_price");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isCartTitleDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartTitle)
        ).isDisplayed();
    }

    public int getCartItemsCount() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)
        ).size();
    }

    public String getFirstItemName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstItemName)
        ).getText();
    }

    public String getFirstItemPrice() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstItemPrice)
        ).getText();
    }

    public void clickCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

    }
}
