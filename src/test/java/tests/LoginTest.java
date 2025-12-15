package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("Authentication")
@Feature("Login Page")
public class LoginTest extends BaseTest {

    @Test(description = "Verify login page UI, security and negative login")
    @Story("Login page validations")
    @Description("Checks UI elements, password masking, placeholders and error message on invalid login")
    public void loginPageAssertionsTest() {

        LoginPage loginPage = new LoginPage(driver);

        verifyLoginPageUI(loginPage);
        verifyPasswordSecurity(loginPage);
        verifyPlaceholders(loginPage);
        verifyNegativeLogin(loginPage);
    }

    // ---------- STEPS ----------

    @Step("Verify login page UI elements are visible")
    public void verifyLoginPageUI(LoginPage loginPage) {
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form არ ჩანს");
        Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Username input არ ჩანს");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input არ ჩანს");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button არ ჩანს");
    }

    @Step("Verify password field is masked")
    public void verifyPasswordSecurity(LoginPage loginPage) {
        Assert.assertEquals(
                loginPage.getPasswordInputType(),
                "password",
                "Password field არ არის masked"
        );
    }

    @Step("Verify input placeholders")
    public void verifyPlaceholders(LoginPage loginPage) {
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), "Username");
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), "Password");
    }

    @Step("Verify error message is shown on invalid login")
    public void verifyNegativeLogin(LoginPage loginPage) {

        loginPage.login("wrong_user", "wrong_password");

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message არ გამოჩნდა არასწორ login-ზე"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo"),
                "URL შეიცვალა არასწორ login-ზე"
        );
    }
}
