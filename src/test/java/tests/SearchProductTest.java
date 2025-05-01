package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchProductTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SearchProductTest.class);

    @Test(priority = 3)
    public void searchProduct() {

        logger.info("Starting 'searchProduct' test");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        try {
            logger.info("Verifying that home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            logger.info("Clicking on 'Products' button");
            homePage.clickProductsButton();

            logger.info("Verifying that user is navigated to ALL PRODUCTS page");
            Assert.assertTrue(productsPage.isAllProductsVisible(), "All Products page is not visible");

            String searchKeyword = "dress";
            logger.info("Searching for product with keyword: {}", searchKeyword);
            productsPage.searchProduct(searchKeyword);

            logger.info("Verifying that 'SEARCHED PRODUCTS' section is visible");
            Assert.assertTrue(productsPage.SearchedProductsVisible(), "'Searched Products' section not visible");

            logger.info("Verifying that searched products are displayed");
            Assert.assertTrue(productsPage.SearchedProductsDisplayed(), "No searched products are displayed");

            logger.info("'searchProductSuccessfully' test passed");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage(), ae);
            throw ae; // Re-throw to ensure the test fails visibly
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        }
    }
}
