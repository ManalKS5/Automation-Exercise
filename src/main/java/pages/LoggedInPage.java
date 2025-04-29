package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInPage {
    WebDriver driver;

    // Locators
    By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");
    By deleteAccountButton = By.xpath("//a[contains(text(),'Delete Account')]");
    By accountDeletedMessage = By.xpath("//b[contains(text(),'Account Deleted!')]");
    By continueButtonAfterDeletion = By.xpath("//a[text()='Continue']");

    // Constructor
    public LoggedInPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify that 'Logged in as username' is visible
    public boolean isLoggedInAsVisible() {
        return driver.findElement(loggedInAsText).isDisplayed();
    }

    // Click 'Delete Account' button
    public void clickDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
    }

    // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedMessage).isDisplayed();
    }

    // Click 'Continue' button
    public void clickContinue() {
        driver.findElement(continueButtonAfterDeletion).click();
    }
}
