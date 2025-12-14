package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTests extends BaseTest {

    @Test
    public void userCanCompleteCheckoutFlowSuccessfully() {

        // ---------- LOGIN ----------
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // ---------- PRODUCTS ----------
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        // ---------- CART ----------
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartTitleDisplayed(), "Cart გვერდი არ გაიხსნა");
        cartPage.clickCheckout();

        // ---------- CHECKOUT: YOUR INFORMATION ----------
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(
                checkoutPage.isCheckoutPageOpened(),
                "Checkout: Your Information გვერდი არ გაიხსნა"
        );

        Assert.assertTrue(checkoutPage.isFirstNameDisplayed(), "First Name ინფუთი არ ჩანს");
        Assert.assertTrue(checkoutPage.isLastNameDisplayed(), "Last Name ინფუთი არ ჩანს");
        Assert.assertTrue(checkoutPage.isPostalCodeDisplayed(), "Postal Code ინფუთი არ ჩანს");

        checkoutPage.fillCheckoutForm("Nugo", "Mukhigulashvili", "1234");

        // ---------- CHECKOUT OVERVIEW ----------
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.waitForOverviewPageToLoad();

        Assert.assertEquals(
                overviewPage.getOverviewTitleText(),
                "Checkout: Overview",
                "Overview გვერდის სათაური არასწორია"
        );

        Assert.assertFalse(
                overviewPage.getItemNames().isEmpty(),
                "Overview გვერდზე პროდუქტები არ ჩანს"
        );

        Assert.assertTrue(
                overviewPage.getFirstItemPriceValue() > 0,
                "პროდუქტის ფასი არასწორია"
        );

        Assert.assertTrue(
                overviewPage.isPaymentInfoSectionDisplayed(),
                "Payment Information სექცია არ ჩანს"
        );

        Assert.assertTrue(
                overviewPage.isShippingInfoSectionDisplayed(),
                "Shipping Information სექცია არ ჩანს"
        );

        Assert.assertEquals(
                overviewPage.getTotalValue(),
                overviewPage.getItemTotalValue() + overviewPage.getTaxValue(),
                0.01,
                "Total არ ემთხვევა Item total + Tax-ს"
        );

        overviewPage.clickFinish();

        // ---------- CHECKOUT COMPLETE ----------
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

        Assert.assertTrue(completePage.isCompleteTitleDisplayed(), "Checkout Complete გვერდი არ გაიხსნა");
        Assert.assertTrue(completePage.isCompleteLogoDisplayed(), "Success logo არ ჩანს");

        Assert.assertEquals(
                completePage.getCompleteHeaderText(),
                "Thank you for your order!",
                "Success header ტექსტი არასწორია"
        );

        Assert.assertFalse(
                completePage.getCompleteHeaderNormalText().isEmpty(),
                "Success description ტექსტი ცარიელია"
        );

        completePage.clickBackButton();
    }
}
