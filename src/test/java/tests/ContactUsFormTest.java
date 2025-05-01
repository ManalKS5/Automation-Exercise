package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsFormTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ContactUsFormTest.class);

    @Test(priority = 2)
    public void contactUsFormSubmission() throws InterruptedException {

        logger.info("Starting Contact Us form submission test");

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        try {
            logger.info("Verifying home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            logger.info("Navigating to Contact Us page");
            homePage.clickContactUsButton();

            logger.info("Checking if 'GET IN TOUCH' is visible");
            Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "'Get in Touch' section not visible");

            logger.info("Filling contact form");
            contactUsPage.fillContactForm(
                    "Manal",
                    "manal@example.com",
                    "Test Subject",
                    "This is a test message for the test case number six."
            );

            logger.info("Uploading file");
            contactUsPage.uploadFile(System.getProperty("user.dir") + "/src/test/resources/testFile.txt");

            logger.info("Submitting form");
            contactUsPage.clickSubmit();

            logger.info("Accepting alert");
            driver.switchTo().alert().accept();

            logger.info("Verifying success message");
            Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message not visible");

            logger.info("Returning to Home page");
            contactUsPage.clickHomeButton();
            Assert.assertTrue(homePage.isHomePageVisible(), "Did not return to Home page successfully");

            logger.info("Contact Us test completed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            logger.error("Unexpected error during Contact Us test: {}", e.getMessage(), e);
        }
    }
}
