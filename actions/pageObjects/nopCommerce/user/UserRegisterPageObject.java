package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public UserHomePageObject clickToLogoutButton() {
		waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getRegisterSuccessMessage() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getExistingMessageAtEmailTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtFirstnameTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitForAllElementsVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

}
