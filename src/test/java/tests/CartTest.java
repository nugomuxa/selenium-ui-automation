package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

@Epic("Cart")
@Feature("Add to Cart")
public class CartTest extends BaseTest {

    @Test(description = "Add product to cart and verify cart data")
    @Story("Add to cart flow")
    @Description("Adds product to cart and verifies cart page data")
    public void addToCartAndVerifyInCartTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartTitleDisplayed());
        Assert.assertEquals(cartPage.getCartItemsCount(), 1);
        Assert.assertFalse(cartPage.getFirstItemName().isBlank());
        Assert.assertTrue(cartPage.getFirstItemPrice().startsWith("$"));
    }
}
