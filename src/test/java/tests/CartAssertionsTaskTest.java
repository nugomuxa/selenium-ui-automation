package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

@Epic("Cart")
@Feature("Cart Assertions")
public class CartAssertionsTaskTest extends BaseTest {

    @Test(description = "Verify cart assertions after adding product")
    @Story("Cart validations")
    @Description("Checks cart title, product count, name and price format")
    public void cartAssertionsTask() {

        login();
        addProductAndOpenCart();
        verifyCartAssertions();
    }

    @Step("Login with valid user")
    public void login() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @Step("Add first product and open cart")
    public void addProductAndOpenCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();
    }

    @Step("Verify cart page assertions")
    public void verifyCartAssertions() {
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartTitleDisplayed(), "Cart გვერდი არ გაიხსნა");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart-ში პროდუქტების რაოდენობა არასწორია");
        Assert.assertFalse(cartPage.getFirstItemName().isBlank(), "პროდუქტის სახელი ცარიელია");
        Assert.assertTrue(cartPage.getFirstItemPrice().startsWith("$"), "ფასი არ იწყება დოლარის ნიშნით");
    }
}
