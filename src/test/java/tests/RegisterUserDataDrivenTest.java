package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelReader;

public class RegisterUserDataDrivenTest extends BaseTest {

    @Test(dataProvider = "RegistrationData", dataProviderClass = ExcelReader.class)
    public void RegisterUserFromExcel(String Title, String Name, String	Email, String Password,
                                      String BirthDay, String BirthMonth, String BirthYear, String FirstName,
                                      String LastName, String Company, String Address1, String Address2,
                                      String Country, String State, String City, String	Zipcode, String MobileNumber){
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
        signupLoginPage.enterNameAndEmail(Name, Email);
        //  Click 'Signup' button
        signupLoginPage.clickSignupButton();
        // Assert error message
        if (signupLoginPage.isEmailAlreadyRegistered()) {
            Allure.step("Email already exists, skipping this registration.");
            throw new SkipException("Email already registered: " + Email);
        }
        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(accountInfoPage.isEnterAccountInfoVisible(), "Account Info section not visible");
        // Fill details: Title, Name, Email, Password, Date of birth
        accountInfoPage.fillAccountInformation(Title,Password, BirthDay, BirthMonth, BirthYear);
        // Select checkbox 'Sign up for our newsletter!'
        accountInfoPage.selectNewsletter();
        // Select checkbox 'Receive special offers from our partners!'
        accountInfoPage.selectSpecialOffers();
        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        accountInfoPage.fillAddressInformation(FirstName, LastName, Company, Address1, Address2, Country, State, City, Zipcode, MobileNumber);
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
