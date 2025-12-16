package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "pony_express")
    private WebElement successLogo;

    @FindBy(className = "complete-header")
    private WebElement headerText;

    @FindBy(className = "complete-text")
    private WebElement descriptionText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Assertions
    public boolean isCompleteTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.isDisplayed();
    }

    public boolean isCompleteLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(successLogo));
        return successLogo.isDisplayed();
    }

    public String getCompleteHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(headerText));
        return headerText.getText().trim();
    }

    public String getCompleteDescriptionText() {
        wait.until(ExpectedConditions.visibilityOf(descriptionText));
        return descriptionText.getText().trim();
    }

    // Actions
    public void clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
        backHomeButton.click();
    }
}
