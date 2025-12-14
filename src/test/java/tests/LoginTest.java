package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginPageAssertionsTest() {

        LoginPage loginPage = new LoginPage(driver);

        //UI assertions
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form არ ჩანს");
        Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Username input არ ჩანს");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input არ ჩანს");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button არ ჩანს");

        //Security assertion
        Assert.assertEquals(
                loginPage.getPasswordInputType(),
                "password",
                "Password field არ არის masked"
        );

        //Placeholder assertions
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), "Username");
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), "Password");

        //Negative login
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
