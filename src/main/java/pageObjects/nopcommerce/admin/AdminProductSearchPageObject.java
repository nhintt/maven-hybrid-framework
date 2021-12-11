package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopcommerce.admin.AdminDashboardPageUI;
import pageUIs.nopcommerce.admin.AdminLoginPageUI;
import pageUIs.nopcommerce.admin.AdminProductDetailPageUI;
import pageUIs.nopcommerce.admin.AdminProductSearchPageUI;

public class AdminProductSearchPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToProductNameTextBox(String productName) {
		waitForElementVisible(driver, AdminProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElement(driver, AdminProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductSearchPageUI.SEARCH_BUTTON);
	}

	public AdminProductDetailPageObject clickToEditButtonByProductName(String productName) {
		waitForElementClickable(driver, AdminProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, AdminProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.getAdminProductDetailPage(driver);
	}

	public boolean isSuccessMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminProductSearchPageUI.SUCCESS_MESSAGE, message);
		return isElementDisplayed(driver, AdminProductSearchPageUI.SUCCESS_MESSAGE, message);
	}

	public boolean isPictureUpdated(String productImageName, String productName) {
		productImageName = productImageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, AdminProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
		return isElementDisplayed(driver, AdminProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
	}
}
