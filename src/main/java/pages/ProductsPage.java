package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    WebDriver driver;

    // Locators
    By allProductsTitle = By.xpath("//h2[contains(text(),'All Products')]");
    By searchInput = By.id("search_product");
    By searchButton = By.id("submit_search");
    By searchedProductsTitle = By.xpath("//h2[contains(text(),'Searched Products')]");
    By searchedProductsList = By.xpath("//div[@class='features_items']/div");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify user is navigated to ALL PRODUCTS page successfully
    public boolean isAllProductsVisible() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver.findElement(allProductsTitle).isDisplayed();
    }

    // Enter product name in search input and click search button
    public void searchProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // Verify 'SEARCHED PRODUCTS' is visible
    public boolean SearchedProductsVisible() {
        return driver.findElement(searchedProductsTitle).isDisplayed();
    }

    // Verify all the products related to search are visible
    public boolean SearchedProductsDisplayed() {
        List<WebElement> products = driver.findElements(searchedProductsList);
        return !products.isEmpty();
    }
}
