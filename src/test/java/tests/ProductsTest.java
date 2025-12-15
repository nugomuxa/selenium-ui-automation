package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

@Epic("Products")
@Feature("Products page")
public class ProductsTest extends BaseTest {

    @Test(description = "Login and add product to cart")
    @Story("Products interaction")
    @Description("Verifies products page and add-to-cart functionality")
    public void successfulLoginAndAddToCartTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertTrue(productsPage.isProductsTitleDisplayed());
        Assert.assertTrue(productsPage.getProductsCount() > 0);

        productsPage.addFirstProductToCart();
        Assert.assertTrue(productsPage.waitForCartBadge());
    }
}
