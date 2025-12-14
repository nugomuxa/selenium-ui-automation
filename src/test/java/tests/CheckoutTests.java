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

        // ---------- CHECKOUT INFORMATION ----------
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(checkoutPage.isCheckoutPageTitleDisplayed(), "Checkout Information გვერდი არ გაიხსნა");
        Assert.assertTrue(checkoutPage.isCheckoutInputContainerDisplayed(), "Checkout ინფუთ ველები არ ჩანს");
        Assert.assertTrue(checkoutPage.isFirstNameInputDisplayed(), "First Name ინფუთი არ ჩანს");
        Assert.assertTrue(checkoutPage.isLastNameInputDisplayed(), "Last Name ინფუთი არ ჩანს");
        Assert.assertTrue(checkoutPage.isPostalCodeInputDisplayed(), "Postal Code ინფუთი არ ჩანს");

        Assert.assertEquals(checkoutPage.getFirstNamePlaceholder(), "FirstName", "First Name placeholder არასწორია");
        Assert.assertEquals(checkoutPage.getLastNamePlaceholder(), "LastName", "Last Name placeholder არასწორია");
        Assert.assertEquals(checkoutPage.getPostalCodePlaceholder(), "Zip/PostalCode", "Postal Code placeholder არასწორია");

        Assert.assertTrue(checkoutPage.isSubmitButtonDisplayed(), "Continue ღილაკი არ ჩანს");

        checkoutPage.fillCheckoutInputs("Nugo", "Mukhigulashvili", "1234");

        // ---------- CHECKOUT OVERVIEW ----------
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);

        Assert.assertTrue(overviewPage.isOverviewTitleDisplayed(), "Checkout Overview გვერდი არ გაიხსნა");
        Assert.assertEquals(overviewPage.getOverviewTitleText(), "Checkout: Overview", "Overview გვერდის სათაური არასწორია");

        Assert.assertFalse(overviewPage.getItemNames().isEmpty(), "Overview გვერდზე პროდუქტები არ ჩანს");

        Assert.assertTrue(overviewPage.getFirstItemPriceValue() > 0, "პროდუქტის ფასი არასწორია");

        Assert.assertTrue(overviewPage.isPaymentInfoSectionDisplayed(), "Payment Information სექცია არ ჩანს");
        Assert.assertTrue(overviewPage.isShippingInfoSectionDisplayed(), "Shipping Information სექცია არ ჩანს");

        Assert.assertTrue(overviewPage.getItemTotalValue() > 0, "Item total არასწორია");
        Assert.assertTrue(overviewPage.getTaxValue() >= 0, "Tax არასწორია");

        Assert.assertEquals(overviewPage.getTotalValue(), overviewPage.getItemTotalValue() + overviewPage.getTaxValue(), 0.01, "Total არ ემთხვევა Item total + Tax-ს");

        overviewPage.clickFinish();

        // ---------- CHECKOUT COMPLETE ----------
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

        Assert.assertTrue(completePage.isCompleteTitleDisplayed(), "Checkout Complete გვერდი არ გაიხსნა");
        Assert.assertTrue(completePage.isCompleteLogoDisplayed(), "Success logo არ ჩანს");

        Assert.assertEquals(completePage.getCompleteHeaderText(), "Thank you for your order!", "Success header ტექსტი არასწორია");

        Assert.assertFalse(completePage.getCompleteHeaderNormalText().isEmpty(), "Success description ტექსტი ცარიელია");

        completePage.clickBackButton();
    }
}
