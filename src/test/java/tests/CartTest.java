package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    @Test
    public void addToCartAndVerifyInCartTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        // Add product
        productsPage.addFirstProductToCart();

        // Go to cart
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        // Cart page assertions
        Assert.assertTrue(cartPage.isCartTitleDisplayed(), "Cart გვერდი არ გაიხსნა");

        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                1,
                "Cart-ში პროდუქტების რაოდენობა არასწორია"
        );

        Assert.assertFalse(
                cartPage.getFirstItemName().isBlank(),
                "Cart item name ცარიელია"
        );

        Assert.assertTrue(
                cartPage.getFirstItemPrice().startsWith("$"),
                "Cart item price არასწორ ფორმატშია"
        );
    }
}
