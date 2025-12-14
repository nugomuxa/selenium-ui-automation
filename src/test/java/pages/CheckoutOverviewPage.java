package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");

    private final By paymentInfoLabel =
            By.xpath("//div[contains(@class,'summary_info_label') and contains(text(),'Payment')]");

    private final By shippingInfoLabel =
            By.xpath("//div[contains(@class,'summary_info_label') and contains(text(),'Shipping')]");

    private final By itemTotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");

    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForOverviewPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel));
    }

    public String getOverviewTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText().trim();
    }

    public List<String> getItemNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames))
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public double getFirstItemPriceValue() {
        String priceText = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemPrices))
                .get(0)
                .getText();
        return parseMoney(priceText);
    }

    public boolean isPaymentInfoSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoLabel)).isDisplayed();
    }

    public boolean isShippingInfoSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingInfoLabel)).isDisplayed();
    }

    public double getItemTotalValue() {
        return parseMoney(wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel)).getText());
    }

    public double getTaxValue() {
        return parseMoney(wait.until(ExpectedConditions.visibilityOfElementLocated(taxLabel)).getText());
    }

    public double getTotalValue() {
        return parseMoney(wait.until(ExpectedConditions.visibilityOfElementLocated(totalLabel)).getText());
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    private double parseMoney(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}
