package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // WebElements
    @FindBy(className = "title")
    private WebElement productsTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartFirstItem;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Assertions
    public boolean isProductsTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productsTitle));
        return productsTitle.isDisplayed();
    }

    public int getProductsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItems));
        return inventoryItems.size();
    }

    public boolean isCartBadgeDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        return cartBadge.isDisplayed();
    }

    // Actions
    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartFirstItem));
        addToCartFirstItem.click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }
}
