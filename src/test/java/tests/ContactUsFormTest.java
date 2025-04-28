package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsFormTest extends BaseTest {

    @Test(priority = 2)
    public void contactUsFormSubmission() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        // Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        // Click on 'Contact Us' button
        homePage.clickContactUsButton();
        // Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "Get in Touch section not visible");
        // Enter name, email, subject and message
        contactUsPage.fillContactForm("Manal", "manal@example.com", "Test Subject", "This is a test message for the test case number six.");
        // Upload file
        contactUsPage.uploadFile(System.getProperty("user.dir") + "/src/test/resources/testFile.txt");
        // Click 'Submit' button
        contactUsPage.clickSubmit();
        // Click OK button
        driver.switchTo().alert().accept();
        // Verify success message 'Success! Your details have been submitted successfully.' is visible
        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message not visible");
        // Click 'Home' button and verify that landed to home page successfully
        contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageVisible(), "Did not return to Home page successfully");
    }
}
