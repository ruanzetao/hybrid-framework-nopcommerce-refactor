package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static UserHomePageObject getHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserRegisterPageObject getRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserAddressPageObject getAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}

	public static UserMyProductReviewsPageObject getMyProductReviewsPage(WebDriver driver) {
		return new UserMyProductReviewsPageObject(driver);
	}

	public static UserRewardPointsPageObject getRewardPointsPage(WebDriver driver) {
		return new UserRewardPointsPageObject(driver);
	}

	public static UserCustomerInforPageObject getCustomerInforPage(WebDriver driver) {
		return new UserCustomerInforPageObject(driver);
	}

	public static UserOrdersPageObject getOrdersPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}

	public static UserDownloadableProductsPageObject getDownloadableProductsPage(WebDriver driver) {
		return new UserDownloadableProductsPageObject(driver);
	}

	public static UserBackInStockSubscriptionsPageObject getBackInStockSubscriptionsPage(WebDriver driver) {
		return new UserBackInStockSubscriptionsPageObject(driver);
	}

	public static UserChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}
}
