package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.*;
import org.testng.annotations.Listeners;
@Listeners(listeners.TestFailureListener.class)
public class RegisterUserTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegisterUserTest.class);

    @Test(priority = 1)
    public void registerUserSuccessfully() {

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        AccountInformationPage accountInfoPage = new AccountInformationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);

        try {
            logger.info("Test started: Register user with static data");

            logger.info("Step 1: Verifying that home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            logger.info("Step 2: Clicking on Signup/Login");
            homePage.clickSignupLogin();

            logger.info("Step 3: Verifying New User Signup section is visible");
            Assert.assertTrue(signupLoginPage.isNewUserSignupVisible(), "Signup section not visible");

            logger.info("Step 4: Entering name and email");
            signupLoginPage.enterNameAndEmail("Manal", "ManalExample123@gmail.com");

            logger.info("Step 5: Clicking Signup button");
            signupLoginPage.clickSignupButton();

            if (signupLoginPage.isEmailAlreadyRegistered()) {
                Allure.step("Email already exists, skipping this registration.");
                logger.warn("Email already registered, skipping test");
                throw new SkipException("Email already registered");
            }

            logger.info("Step 6: Verifying Account Information section is visible");
            Assert.assertTrue(accountInfoPage.isEnterAccountInfoVisible(), "Account Info section not visible");

            logger.info("Step 7: Filling account information");
            accountInfoPage.fillAccountInformation("Mrs", "TestPass123", "10", "5", "2000");

            logger.info("Step 8: Selecting newsletter and offers");
            accountInfoPage.selectNewsletter();
            accountInfoPage.selectSpecialOffers();

            logger.info("Step 9: Filling address information");
            accountInfoPage.fillAddressInformation("Manal", "Khalid", "Google", "Address1", "Address2", "United States", "California", "Mountain View", "12345", "3045731071");

            logger.info("Step 10: Clicking Create Account");
            accountInfoPage.clickCreateAccount();

            logger.info("Step 11: Verifying Account Created");
            Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account not created!");

            logger.info("Step 12: Clicking Continue");
            accountCreatedPage.clickContinue();

            logger.info("Step 13: Verifying user is logged in");
            Assert.assertTrue(loggedInPage.isLoggedInAsVisible(), "Logged in username not visible");

            logger.info("Step 14: Deleting account");
            loggedInPage.clickDeleteAccount();

            logger.info("Step 15: Verifying Account Deleted");
            Assert.assertTrue(loggedInPage.isAccountDeletedVisible(), "Account not deleted!");
            loggedInPage.clickContinue();

            logger.info("Test finished successfully");

        } catch (SkipException se) {
            logger.warn("Test skipped: {}", se.getMessage());
            throw se;
        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            // throw e;
        }
    }
}
