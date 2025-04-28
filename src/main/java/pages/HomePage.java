package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
    By contactUsButton = By.xpath("//*[contains(text(),' Contact us')]");
    By productsButton = By.xpath("//*[contains(text(),' Products')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify that home page is visible successfully
    public boolean isHomePageVisible() {
        return driver.findElement(signupLoginButton).isDisplayed();
    }

    // Click on 'Signup / Login' button
    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }

    // Click Contact Us Button
    public void clickContactUsButton() {
        driver.findElement(contactUsButton).click();
    }

    public void clickProductsButton() {
        driver.findElement(productsButton).click();
    }
}
