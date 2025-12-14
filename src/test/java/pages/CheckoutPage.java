package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By checkoutInputContainer = By.className("checkout-info");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By submitButton = By.className("submit-button");
    private By checkoutPageTitle = By.className("title");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // UI visibility checks
    public boolean isCheckoutInputContainerDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkoutInputContainer)
        ).isDisplayed();
    }

    public boolean isFirstNameInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        ).isDisplayed();
    }

    public boolean isLastNameInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameInput)
        ).isDisplayed();
    }

    public boolean isPostalCodeInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCodeInput)
        ).isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(submitButton)
        ).isDisplayed();
    }

    public boolean isCheckoutPageTitleDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkoutPageTitle)
        ).isDisplayed();
    }

    // Field attributes
    public String getFirstNamePlaceholder() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(firstNameInput)
        ).getAttribute("placeholder");
    }

    public String getLastNamePlaceholder() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(lastNameInput)
        ).getAttribute("placeholder");
    }

    public String getPostalCodePlaceholder() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(postalCodeInput)
        ).getAttribute("placeholder");
    }

    // Actions
    public void enterFirstName(String firstName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        ).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameInput)
        ).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCodeInput)
        ).sendKeys(postalCode);
    }


    public void clickSubmitButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
    }

    public void fillCheckoutInputs(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickSubmitButton();
    }
}
