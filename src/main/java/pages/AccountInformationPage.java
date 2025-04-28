package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountInformationPage {

    WebDriver driver;

    //Locators
    // Enter Account Information
    By enterAccountInformationText = By.xpath("//*[contains(text(),'Enter Account Information')]");
    By femaleGender = By.id("id_gender2");
    By maleGender = By.id("id_gender1");
    By passwordInput = By.id("password");
    By daysDropdown = By.id("days");
    By monthsDropdown = By.id("months");
    By yearsDropdown = By.id("years");
    By newsletterCheckbox = By.id("newsletter");
    By specialOffersCheckbox = By.id("optin");

    // address information
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By companyInput = By.id("company");
    By address1Input = By.id("address1");
    By address2Input = By.id("address2");
    By countryDropdown = By.id("country");
    By stateInput = By.id("state");
    By cityInput = By.id("city");
    By zipcodeInput = By.id("zipcode");
    By mobileNumberInput = By.id("mobile_number");

    // Create Account button
    By createAccountButton = By.xpath("//button[@data-qa='create-account']");

    // Constructor
    public AccountInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify that 'ENTER ACCOUNT INFORMATION' is visible
    public boolean isEnterAccountInfoVisible() {
        return driver.findElement(enterAccountInformationText).isDisplayed();
    }

    // Fill details: Title, Name, Email, Password, Date of birth
    public void fillAccountInformation(String title,String password, String day, String month, String year) {
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(maleGender).click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            driver.findElement(femaleGender).click();
        }
        driver.findElement(passwordInput).sendKeys(password);
        new Select(driver.findElement(daysDropdown)).selectByValue(day);
        new Select(driver.findElement(monthsDropdown)).selectByValue(month);
        new Select(driver.findElement(yearsDropdown)).selectByValue(year);
    }

    // Select checkbox 'Sign up for our newsletter!'
    public void selectNewsletter() {
        driver.findElement(newsletterCheckbox).click();
    }

    // Select checkbox 'Receive special offers from our partners!'
    public void selectSpecialOffers() {
        driver.findElement(specialOffersCheckbox).click();
    }

    // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
    public void fillAddressInformation(String firstName, String lastName, String company, String address1, String address2,
                                       String country, String state, String city, String zipcode, String mobileNumber) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(address1Input).sendKeys(address1);
        driver.findElement(address2Input).sendKeys(address2);
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zipcode);
        driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
    }

    // Click 'Create Account button'
    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

}
