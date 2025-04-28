package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class SearchProductTest extends BaseTest {

    @Test(priority = 3)
    public void searchProductSuccessfully() {

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        // Click on 'Products' button
        homePage.clickProductsButton();
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.isAllProductsVisible(), "All Products page is not visible");
        // Enter product name in search input and click search button
        productsPage.searchProduct("dress");
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.SearchedProductsVisible(), "Searched Products section not visible");
        // Verify all the products related to search are visible
        Assert.assertTrue(productsPage.SearchedProductsDisplayed(), "No searched products are displayed");
    }
}

