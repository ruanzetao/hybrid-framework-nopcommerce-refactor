package user;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Register_Page_Object extends BaseTest {
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, lastName, password;

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		getBrowserDriver(browserName);

		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
//		homePage = PageGeneratorManager.getHomePage(driver);
//		registerPage = PageGeneratorManager.getRegisterPage(driver);

		firstName = "SangNg";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "mailinator.com";
		password = "123456";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		// Assert something
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("afc9999@!mailinator.com");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
		registerPage.clickToLogoutButton();
	}
//
//	public void TC_03_Register_Success() {
//		registerPage = homePage.clickToRegisterLink();
//		registerPage.inputToFirstNameTextbox(firstName);
//		registerPage.inputToLastNameTextbox(lastName);
//		registerPage.inputToEmailTextbox(emailAddress);
//		registerPage.inputToPasswordTextbox(password);
//		registerPage.inputToConfirmPasswordTextbox(password);
//		registerPage.clickToRegisterButton();
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		registerPage.clickToLogoutButton();
//	}

//	@Test
//	public void TC_04_Register_Existing_Email() {
//
//	}
//
//	@Test
//	public void TC_05_Register_Password_Less_Than_6_Characters() {
//
//	}
//
//	@Test
//	public void TC_06_Register_Incorrect_Confirm_Password() {
//
//	}

//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
}