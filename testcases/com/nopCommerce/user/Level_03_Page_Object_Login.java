package com.nopCommerce.user;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_Login extends BaseTest {
	String projectPath = System.getProperty("user.dir");
	String emailAddress, invalidEmail, notFoundEmail, firstName, lastName, password;

	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		getBrowserDriver(browserName);

		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
//		homePage = PageGeneratorManager.getHomePage(driver);
//		registerPage = PageGeneratorManager.getRegisterPage(driver);

		firstName = "SangNg";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		password = "123456";

		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutButton();
	}

	@Test
	public void TC_01_Login_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.clickToLoginButton();
		// Assert something
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		invalidEmail = "checkinbysang@mailinator.";
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		// Assert something
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Login_Email_Not_Found() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		notFoundEmail = "checkinbysang@mailinator.com";
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		// Assert something
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_Existing_Email_Empty_Password() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		// Assert something
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Existing_Email_Incorrect_Password() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox("12345678");
		loginPage.clickToLoginButton();
		// Assert something
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Successful() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		// Assert something
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
