package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By pageTitle = By.className("title");

    // Items
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");

    // Summary info
    private final By paymentInfoLabel = By.xpath("//div[contains(@class,'summary_info_label') and normalize-space()='Payment Information']");
    private final By shippingInfoLabel = By.xpath("//div[contains(@class,'summary_info_label') and normalize-space()='Shipping Information']");


    private final By summaryValueLabels = By.className("summary_value_label");

    // Totals
    private final By itemTotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");

    // Actions
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Basic page checks
    public boolean isOverviewTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public String getOverviewTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText().trim();
    }

    // Items getters
    public List<String> getItemNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNames));
        List<WebElement> elements = driver.findElements(itemNames);

        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public String getFirstItemName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNames));
        return driver.findElements(itemNames).get(0).getText().trim();
    }

    public List<String> getItemPricesText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrices));
        List<WebElement> elements = driver.findElements(itemPrices);

        List<String> prices = new ArrayList<>();
        for (WebElement el : elements) {
            prices.add(el.getText().trim());
        }
        return prices;
    }

    public double getFirstItemPriceValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrices));
        String priceText = driver.findElements(itemPrices).get(0).getText().trim(); // "$29.99"
        return parseMoney(priceText);
    }

    //Payment / Shipping getters
    public boolean isPaymentInfoSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoLabel)).isDisplayed();
    }

    public boolean isShippingInfoSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingInfoLabel)).isDisplayed();
    }

    public String getPaymentInfoValueText() {
        // Payment value is the first summary_value_label
        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryValueLabels));
        return driver.findElements(summaryValueLabels).get(0).getText().trim(); // e.g. "SauceCard #31337"
    }

    public String getShippingInfoValueText() {
        // Shipping value is the second summary_value_label
        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryValueLabels));
        return driver.findElements(summaryValueLabels).get(1).getText().trim(); // e.g. "FREE PONY EXPRESS DELIVERY!"
    }

    // Totals getters (text)
    public String getItemTotalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel)).getText().trim();
    }

    public String getTaxText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(taxLabel)).getText().trim();
    }

    public String getTotalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalLabel)).getText().trim();
    }

    //Totals getters (numeric)
    public double getItemTotalValue() {
        // "Item total: $29.99"
        return parseMoney(getItemTotalText());
    }

    public double getTaxValue() {
        // "Tax: $2.40"
        return parseMoney(getTaxText());
    }

    public double getTotalValue() {
        // "Total: $32.39"
        return parseMoney(getTotalText());
    }

    // Actions
    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }


    private static double parseMoney(String text) {
        String normalized = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(normalized);
    }
}
