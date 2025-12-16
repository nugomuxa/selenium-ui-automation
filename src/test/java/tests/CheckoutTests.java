package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

@Epic("Checkout")
@Feature("E2E Checkout")
public class CheckoutTests extends BaseTest {

    @Test(description = "User can complete checkout successfully")
    @Story("Complete checkout flow")
    @Description("Full E2E: login → add product → checkout → success page")
    public void userCanCompleteCheckoutFlowSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page did not open");
        checkoutPage.fillCheckoutForm("Nugo", "Mukhigulashvili", "1234");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.waitForOverviewPageToLoad();
        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

        Assert.assertTrue(completePage.isCompleteTitleDisplayed());
        Assert.assertEquals(completePage.getCompleteHeaderText(), "Thank you for your order!");
    }
}
