package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage  {

    WebDriver driver;

    // Locators
    By newUserSignupText = By.xpath("//h2[contains(text(),'New User Signup!')]");
    By nameInput = By.xpath("//*[@name='name']");
    By emailInput = By.xpath("//input[@data-qa='signup-email']");
    By signupButton = By.xpath("//button[contains(text(),'Signup')]");

    // Constructor
    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify 'New User Signup!' is visible
    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupText).isDisplayed();
    }

    // Enter name and email address
    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    // Click 'Signup' button
    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}
