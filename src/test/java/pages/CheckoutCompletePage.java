package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Locators

    private final By completePageTitle = By.className("title");
    private final By completePageLogo = By.className("pony_express");
    private final By completeHeaderBoldText = By.className("complete-header");
    private final By completeHeaderNormalText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //page checks
    public boolean isCompleteTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completePageTitle)).isDisplayed();
    }

    public boolean isCompleteLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completePageLogo)).isDisplayed();
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeaderBoldText)).getText().trim();
    }

    public String getCompleteHeaderNormalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeaderNormalText)).getText().trim();
    }

    public void clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
    }

}


