package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartAssertionsTaskTest extends BaseTest {

    @Test
    public void cartAssertionsTask() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartTitleDisplayed(),
                "Cart გვერდი არ გაიხსნა"
        );

        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                1,
                "Cart-ში პროდუქტების რაოდენობა არასწორია"
        );

        Assert.assertFalse(cartPage.getFirstItemName().isBlank());
        Assert.assertTrue(cartPage.getFirstItemPrice().startsWith("$"),
                "პროდუქტის ფასი არ იწყება დოლარის ნიშნით"
        );


    }
}


