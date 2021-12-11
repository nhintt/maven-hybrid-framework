package pageObjects.jQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageByNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
	}

	public boolean isPageActiveByNumber(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGING_ACTIVE_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGING_ACTIVE_BY_NUMBER, pageNumber);
	}

	public void inputToHeaderTextboxByName(String labelName, String textValue) {
		waitForElementVisible(driver, HomePageUI.LABEL_TEXTBOX_BY_NAME, labelName);
		sendkeyToElement(driver, HomePageUI.LABEL_TEXTBOX_BY_NAME, textValue, labelName);
		pressKeyToElement(driver, HomePageUI.LABEL_TEXTBOX_BY_NAME, Keys.ENTER, labelName);
	}

	public void clickToIconByCountryName(String countryName, String iconType) {
		waitForElementClickable(driver, HomePageUI.ICON_BY_COUNTRY_NAME, countryName, iconType);
		clickToElement(driver, HomePageUI.ICON_BY_COUNTRY_NAME, countryName, iconType, iconType, null);
	}

	public boolean isRowValueDisplayed(String female, String country, String male, String total) {
		waitForElementClickable(driver, HomePageUI.ROW_VALUE_BY_FEMALE_COUNTRY_MALE_TOTAL, female, country, male, total);
		return isElementDisplayed(driver, HomePageUI.ROW_VALUE_BY_FEMALE_COUNTRY_MALE_TOTAL, female, country, male, total);
	}

	public void inputToTextboxByRowNumber(String textValue, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, HomePageUI.HEADER_NAME_INDEX, headerName) + 1;
		String locator = getDynamicLocator(HomePageUI.TEXTBOX_BY_COLUMN_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		waitForElementVisible(driver, locator);
		if (headerName.equals("Member Since")) {
			removeAttributeInDOM(driver, locator, "date");
		}
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_ROW_INDEX, textValue, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToIconActionByRowNumber(String iconTitle, String rowIndex) {
		waitForElementVisible(driver, HomePageUI.ACRION_BUTTON_BY_ROW_INDEX, rowIndex, iconTitle);
		clickToElement(driver, HomePageUI.ACRION_BUTTON_BY_ROW_INDEX, rowIndex, iconTitle);
	}
}
