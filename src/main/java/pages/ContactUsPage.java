package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {

    WebDriver driver;

    // Locators
    By getInTouchText = By.xpath("//h2[contains(text(),'Get In Touch')]");
    By nameInput = By.name("name");
    By emailInput = By.name("email");
    By subjectInput = By.name("subject");
    By messageInput = By.id("message");
    By uploadFileInput = By.name("upload_file");
    By submitButton = By.xpath("//input[@name='submit']");
    By successMessage = By.xpath("//div[contains(text(),'Success! Your details have been submitted successfully.')]");
    By homeButton = By.xpath("//a[contains(text(),'Home')]");

    // Constructor
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify 'GET IN TOUCH' is visible
    public boolean isGetInTouchVisible() {
        return driver.findElement(getInTouchText).isDisplayed();
    }

    // Enter name, email, subject and message
    public void fillContactForm(String name, String email, String subject, String message) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(subjectInput).sendKeys(subject);
        driver.findElement(messageInput).sendKeys(message);
    }

    // Upload file
    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    // Click 'Submit' button
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // Verify success message 'Success! Your details have been submitted successfully.' is visible
    public boolean isSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    // Click 'Home' button and verify that landed to home page successfully
    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }
}
