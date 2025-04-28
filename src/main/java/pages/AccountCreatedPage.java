package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    WebDriver driver;

    // Locators
    By accountCreatedText = By.xpath("//b[contains(text(),'Account Created!')]");
    By continueButton = By.xpath("//a[contains(text(),'Continue')]");

    // Constructor
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify that 'ACCOUNT CREATED!' is visible
    public boolean isAccountCreatedVisible() {
        return driver.findElement(accountCreatedText).isDisplayed();
    }

    // Click 'Continue' button
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
}
