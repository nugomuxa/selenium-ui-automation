package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By successLogo = By.className("pony_express");
    private final By headerText = By.className("complete-header");
    private final By descriptionText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCompleteTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public boolean isCompleteLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successLogo)).isDisplayed();
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerText)).getText().trim();
    }

    public String getCompleteHeaderNormalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionText)).getText().trim();
    }

    public void clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
    }
}
