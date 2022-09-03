package user;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewsPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, lastName, password;

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private AddressPageObject addressPage;
	private MyProductReviewsPageObject myProductReviewsPage;
	private CustomerInforPageObject customerInforPage;
	private RewardPointsPageObject rewardPointsPage;

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
