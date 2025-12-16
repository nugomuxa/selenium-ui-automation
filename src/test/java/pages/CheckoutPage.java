package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // WebElements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Assertions
    public boolean isCheckoutPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText().contains("Checkout: Your Information");
    }

    public boolean isFirstNameDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return firstNameInput.isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        return lastNameInput.isDisplayed();
    }

    public boolean isPostalCodeDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(postalCodeInput));
        return postalCodeInput.isDisplayed();
    }

    // Actions
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOf(postalCodeInput));
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);

        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
}
