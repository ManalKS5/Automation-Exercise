package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import utils.CSVReader;

public class ContactUsDataDrivenTest extends BaseTest {


    @Test (dataProvider = "ContactUsData", dataProviderClass = CSVReader.class)
    public void contactUsFromCSV(String name, String email, String subject, String message){

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        // Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        // Click on 'Contact Us' button
        homePage.clickContactUsButton();
        // Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "Get in Touch section not visible");
        // Enter name, email, subject and message
        contactUsPage.fillContactForm(name, email, subject, message);
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
