package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page title
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Items
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    // Summary sections
    @FindBy(xpath = "//div[contains(@class,'summary_info_label') and contains(text(),'Payment')]")
    private WebElement paymentInfoLabel;

    @FindBy(xpath = "//div[contains(@class,'summary_info_label') and contains(text(),'Shipping')]")
    private WebElement shippingInfoLabel;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    // Actions
    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Page load
    public void waitForOverviewPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Checkout: Overview"));
    }


    // Getters / Assertions
    public String getOverviewTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText().trim();
    }

    public List<String> getItemNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemNames));
        return itemNames.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
    }

    public double getFirstItemPriceValue() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        return parseMoney(itemPrices.get(0).getText());
    }

    public boolean isPaymentInfoSectionDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(paymentInfoLabel));
        return paymentInfoLabel.isDisplayed();
    }

    public boolean isShippingInfoSectionDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(shippingInfoLabel));
        return shippingInfoLabel.isDisplayed();
    }

    public double getItemTotalValue() {
        wait.until(ExpectedConditions.visibilityOf(itemTotalLabel));
        return parseMoney(itemTotalLabel.getText());
    }

    public double getTaxValue() {
        wait.until(ExpectedConditions.visibilityOf(taxLabel));
        return parseMoney(taxLabel.getText());
    }

    public double getTotalValue() {
        wait.until(ExpectedConditions.visibilityOf(totalLabel));
        return parseMoney(totalLabel.getText());
    }

    // Actions
    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }

    // Utils
    private double parseMoney(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}
