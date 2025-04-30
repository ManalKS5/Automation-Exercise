package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.*;

public class RegisterUserTest extends BaseTest {

    @Test(priority = 1)
    public void registerUserSuccessfully() {

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        AccountInformationPage accountInfoPage = new AccountInformationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);


        // Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        // Click on 'Signup / Login' button
        homePage.clickSignupLogin();
        // Verify 'New User Signup!' is visible
        Assert.assertTrue(signupLoginPage.isNewUserSignupVisible(), "Signup section not visible");
        // Enter name and email address
        signupLoginPage.enterNameAndEmail("Manal", "ManalExample123@gmail.com");
        //  Click 'Signup' button
        signupLoginPage.clickSignupButton();
        // Assert error message
        if (signupLoginPage.isEmailAlreadyRegistered()) {
            Allure.step("Email already exists, skipping this registration.");
            throw new SkipException("Email already registered: ");
        }
        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(accountInfoPage.isEnterAccountInfoVisible(), "Account Info section not visible");
        // Fill details: Title, Name, Email, Password, Date of birth
        accountInfoPage.fillAccountInformation("Mrs","TestPass123", "10", "5", "2000");
        // Select checkbox 'Sign up for our newsletter!'
        accountInfoPage.selectNewsletter();
        // Select checkbox 'Receive special offers from our partners!'
        accountInfoPage.selectSpecialOffers();
        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        accountInfoPage.fillAddressInformation("Manal", "Khalid", "Google", "Address1", "Address2", "United States", "California", "Mountain View", "12345", "3045731071");
        // Click 'Create Account button'
        accountInfoPage.clickCreateAccount();
        // Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account not created!");
        // Click 'Continue' button
        accountCreatedPage.clickContinue();
        // Verify that 'Logged in as username' is visible
        Assert.assertTrue(loggedInPage.isLoggedInAsVisible(), "Logged in username not visible");
        // Click 'Delete Account' button
        loggedInPage.clickDeleteAccount();
        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertTrue(loggedInPage.isAccountDeletedVisible(), "Account not deleted!");
        loggedInPage.clickContinue();

    }
}
