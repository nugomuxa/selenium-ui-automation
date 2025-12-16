package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // WebElements
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "login_button_container")
    private WebElement loginForm;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // UI visibility
    public boolean isLoginFormDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        return loginForm.isDisplayed();
    }

    public boolean isUsernameInputDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        return usernameInput.isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        return passwordInput.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return loginButton.isDisplayed();
    }

    // Field attributes
    public String getPasswordInputType() {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        return passwordInput.getAttribute("type");
    }

    public String getUsernamePlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        return usernameInput.getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        return passwordInput.getAttribute("placeholder");
    }

    // Actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Error handling
    public boolean isErrorMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
