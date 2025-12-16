package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page elements
    @FindBy(className = "title")
    private WebElement cartTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Assertions
    public boolean isCartTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartTitle));
        return cartTitle.isDisplayed();
    }

    public int getCartItemsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public String getFirstItemName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemNames));
        return itemNames.get(0).getText().trim();
    }

    public String getFirstItemPrice() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        return itemPrices.get(0).getText().trim();
    }

    // Actions
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}
