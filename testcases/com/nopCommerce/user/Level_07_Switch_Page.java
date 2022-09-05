package com.nopCommerce.user;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, lastName, password;

	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserRewardPointsPageObject rewardPointsPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		firstName = "SangNg";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		password = "123456";

	}

	@Test
	public void TC_01_Register() {
		// Register an Account
		registerPage = homePage.clickToRegisterLink();
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
	public void TC_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
	}

	@Test
	public void TC_03_CustomerInfor() {
		customerInforPage = homePage.clickToMyAccountLink();
		// Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void TC_04_Switch_Page() {
		// Customer Infor => Address
		addressPage = customerInforPage.openAddressPage(driver);
		// Address => My product review
		myProductReviewsPage = addressPage.openMyProductReviewsPage(driver);
		// My prod review => reward point
		rewardPointsPage = myProductReviewsPage.openRewardPointsPage(driver);
		// Reward point => Address
		addressPage = rewardPointsPage.openAddressPage(driver);
		// My product review => Address
		addressPage = myProductReviewsPage.openAddressPage(driver);
		// Address => Reward
		rewardPointsPage = addressPage.openRewardPointsPage(driver);
		// Reward => My product review
		myProductReviewsPage = rewardPointsPage.openMyProductReviewsPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
