package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {

    @Test
    public void successfulLoginAndAddToCartTest() {

        LoginPage loginPage = new LoginPage(driver);

        //Positive login
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        //Dashboard assertions
        Assert.assertTrue(
                productsPage.isProductsTitleDisplayed(),
                "Products გვერდი არ გაიხსნა"
        );

        Assert.assertTrue(
                productsPage.getProductsCount() > 0,
                "პროდუქტები არ ჩანს"
        );

        //Add to cart
        productsPage.addFirstProductToCart();

        Assert.assertTrue(
                productsPage.isCartBadgeDisplayed(),
                "Cart badge არ გამოჩნდა პროდუქტის დამატების შემდეგ"
        );

    }

}
