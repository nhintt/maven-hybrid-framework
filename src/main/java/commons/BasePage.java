package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.user.UserAddressPageObject;
import pageObjects.nopcommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserOrderPageObject;
import pageObjects.nopcommerce.user.UserRewardPointsPageObject;
import pageUIs.hrm.BasePageUI;
import pageUIs.hrm.LoginPageUI;
import pageUIs.nopcommerce.admin.AdminBasePageUI;
import pageUIs.nopcommerce.user.UserBasePageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPrecence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPrecence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPrecence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPrecence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPrecence(driver).sendKeys(textValue);
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentPageID);
	}

	public void switchToWindowByTitle(WebDriver driver, String tabeTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(tabeTitle))
				break;
		}
	}

	public void switchToWindowByID(WebDriver driver, String pageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(pageID)) {
				driver.switchTo().window(window);
			}
		}
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(xpathLocator, params)));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator, String... params) {
		return driver.findElements(By.xpath(getDynamicLocator(xpathLocator, params)));
	}

	public String getDynamicLocator(String xpathLocator, String... params) {
		return String.format(xpathLocator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... params) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, getDynamicLocator(xpathLocator, params));
			sleepInSecond(2);
		} else {
			getWebElement(driver, getDynamicLocator(xpathLocator, params)).click();
		}
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathLocator);
			sleepInSecond(2);
		} else {
			getWebElement(driver, xpathLocator).click();
		}
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);
	}

	public void selectItemInDropdownByText(WebDriver driver, String xpathLocator, String textItem, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultipleDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	public String getElementText(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getText();
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... params) {
		return getListWebElement(driver, xpathLocator, params).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElement(driver, getDynamicLocator(xpathLocator, params));
			} else {
				element.click();
			}
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator, String... params) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicLocator(xpathLocator, params))).perform();
	}

	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... params) {
		Actions action = new Actions(driver);
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		action.sendKeys(element, key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return jQuery.active === 0;");
			}

		};
		return explicitWait.until(jQueryLoaded);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator, String params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void overrideGlobalTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> listElements = getListWebElement(driver, xpathLocator);
		overrideGlobalTimeout(driver, longTimeout);

		if (listElements.size() == 0) {
			return true;
		} else if (listElements.size() > 0 && !listElements.get(0).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	// USER-NOPCOMMERCE
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADDRESS_LINK);
		clickToElement(driver, UserBasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserOrderPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ORDER_LINK);
		clickToElement(driver, UserBasePageUI.ORDER_LINK);
		return PageGeneratorManager.getUserOrderPage(driver);
	}

	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.REWARD_POINTS_LINK);
		clickToElement(driver, UserBasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointsPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}

	public void openHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
	}

	public void inputToTextboxByID(WebDriver driver, String textValue, String... params) {
		String locator = getDynamicLocator(UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, params);
		waitForElementVisible(driver, locator);
		sendkeyToElement(driver, locator, textValue);
	}

	// ADMIN-NOPCOMMERCE
	public void openSubMenuPageByName(WebDriver driver, String menuName, String subMenuName) {
		waitForElementVisible(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuName);
		clickToElement(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuName);

		waitForElementVisible(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuName);
		clickToElement(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuName);
	}

	public void uploadFileAtCardName(WebDriver driver, String cardName, String... fileNames) {
		String filePath = GlobalContants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, AdminBasePageUI.UPPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);
	}

	public boolean isMessageDisplayedInEmptyTable(WebDriver driver, String tableName) {
		waitForElementVisible(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
		return isElementDisplayed(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
	}

	// HRM
	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}

	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}

	public void inputToTextboxByID(WebDriver driver, String textboxID, String textValue) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, textValue, textboxID);
	}

	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonID);
	}

	public void openMenuPageByText(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
	}

	public void openSubMenuPage(WebDriver driver, String menuPageName, String submenuPageName) {
		openMenuPageByText(driver, menuPageName);
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, submenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, submenuPageName);
	}

	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String submenuPageName, String childSubmenuName) {
		openMenuPageByText(driver, menuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, submenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, submenuPageName);

		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubmenuName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubmenuName);
	}

	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxID);
	}

	public void clickToCheckboxByLabel(WebDriver driver, String label) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, label);
		checkToDefaultCheckboxRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, label);
	}

	public void clickToRadioByLabel(WebDriver driver, String label) {
		// waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, label);
		checkToDefaultCheckboxRadio(driver, BasePageUI.RADIO_BY_LABEL, label);
	}

	public void selectToDropdownByID(WebDriver driver, String dropdownID, String textItem) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDropdownByText(driver, BasePageUI.DROPDOWN_BY_ID, textItem, dropdownID);
	}

	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, columnName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_ROW_INDEX_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_ROW_INDEX_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}

	public void uploadImage(WebDriver driver, String imagePath) {
		getWebElement(driver, BasePageUI.UPLOAD_IMG_INPUT).sendKeys(imagePath);
	}

	public boolean isFieldEnabledByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}

	public boolean isSuccessMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, BasePageUI.UPLOAD_SUCCESSFULLY_MESSAGE, message);
		return isElementDisplayed(driver, BasePageUI.UPLOAD_SUCCESSFULLY_MESSAGE, message);
	}

	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, labelName);
	}

	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
	}

	private WebDriverWait explicitWait;
	private int longTimeout = 30;
	private int shortTimeout = 5;
}
