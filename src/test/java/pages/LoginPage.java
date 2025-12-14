package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginForm = By.id("login_button_container");
    private By errorMessage = By.cssSelector("[data-test='error']");

    //Constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //UI visibility
    public boolean isLoginFormDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginForm)
        ).isDisplayed();
    }

    public boolean isUsernameInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        ).isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }

    //Field attributes
    public String getPasswordInputType() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(passwordInput)
        ).getAttribute("type");
    }

    public String getUsernamePlaceholder() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(usernameInput)
        ).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(passwordInput)
        ).getAttribute("placeholder");
    }

    //Actions
    public void enterUsername(String username) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        ).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    //Error handling
    public boolean isErrorMessageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).isDisplayed();
    }

    public String getErrorMessageText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }
}
