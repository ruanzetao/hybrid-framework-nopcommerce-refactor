package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private long longTimeout = 30;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String gerPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected void getAlertText(WebDriver driver) {
		waitForAlertPresence(driver).getText();
	}

	protected void sendKeysToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	protected By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	protected void sendKeysToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	// Thiếu items of Dropdown

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementCSSValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	protected String convertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected void checkToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathlocator)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathlocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathlocator)));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathlocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathlocator)));
	}
}
