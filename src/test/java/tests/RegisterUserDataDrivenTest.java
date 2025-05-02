package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelReader;
import org.testng.annotations.Listeners;
@Listeners(listeners.TestFailureListener.class)
public class RegisterUserDataDrivenTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegisterUserDataDrivenTest.class);

    @Test(dataProvider = "RegistrationData", dataProviderClass = ExcelReader.class)
    public void RegisterUserFromExcel(String Title, String Name, String Email, String Password,
                                      String BirthDay, String BirthMonth, String BirthYear, String FirstName,
                                      String LastName, String Company, String Address1, String Address2,
                                      String Country, String State, String City, String Zipcode, String MobileNumber) {

        logger.info("Starting registration test for: {}", Email);
        Allure.step("Starting test for: " + Email);

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        AccountInformationPage accountInfoPage = new AccountInformationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);

        try {
            logger.info("Verifying home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            logger.info("Navigating to Signup/Login page");
            homePage.clickSignupLogin();

            logger.info("Checking if 'New User Signup!' is visible");
            Assert.assertTrue(signupLoginPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

            logger.info("Entering Name and Email: {}, {}", Name, Email);
            signupLoginPage.enterNameAndEmail(Name, Email);
            signupLoginPage.clickSignupButton();

            if (signupLoginPage.isEmailAlreadyRegistered()) {
                logger.warn("Email already registered: {}", Email);
                Allure.step("Email already registered: " + Email);
                throw new SkipException("Skipping - email already registered: " + Email);
            }

            logger.info("Verifying 'Enter Account Information' section is visible");
            Assert.assertTrue(accountInfoPage.isEnterAccountInfoVisible(), "Account Info section not visible");

            logger.info("Filling account information");
            accountInfoPage.fillAccountInformation(Title, Password, BirthDay, BirthMonth, BirthYear);
            accountInfoPage.selectNewsletter();
            accountInfoPage.selectSpecialOffers();

            logger.info("Filling address information");
            accountInfoPage.fillAddressInformation(FirstName, LastName, Company, Address1, Address2, Country, State, City, Zipcode, MobileNumber);

            logger.info("Submitting form to create account");
            accountInfoPage.clickCreateAccount();

            logger.info("Verifying account creation");
            Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account not created!");

            accountCreatedPage.clickContinue();

            logger.info("Verifying logged in as {}", Name);
            Assert.assertTrue(loggedInPage.isLoggedInAsVisible(), "Logged in user info not visible");

            logger.info("Deleting created account");
            loggedInPage.clickDeleteAccount();

            logger.info("Verifying account deletion");
            Assert.assertTrue(loggedInPage.isAccountDeletedVisible(), "Account deletion failed");
            loggedInPage.clickContinue();

            logger.info("Test finished successfully for {}", Email);
            Allure.step("Registration and deletion completed for: " + Email);

        } catch (AssertionError ae) {
            logger.error("Assertion failed for {}: {}", Email, ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            logger.error("Unexpected error for {}: {}", Email, e.getMessage(), e);
        }
    }
}
