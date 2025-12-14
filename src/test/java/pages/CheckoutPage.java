package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCheckoutPageOpened() {
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitle,
                        "Checkout: Your Information"
                )
        );
    }

    public boolean isFirstNameDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).isDisplayed();
    }

    public boolean isPostalCodeDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput)).isDisplayed();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput)).sendKeys(postalCode);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
