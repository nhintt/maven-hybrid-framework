package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopcommerce.admin.AdminDashboardPageUI;
import pageUIs.nopcommerce.admin.AdminLoginPageUI;
import pageUIs.nopcommerce.admin.AdminProductDetailPageUI;

public class AdminProductDetailPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToExpandPanelByName(String panelName) {
		waitForElementVisible(driver, AdminProductDetailPageUI.TOGGLE_ICON_BY_CARD_NAME, panelName);
		if (getElementAttribute(driver, AdminProductDetailPageUI.TOGGLE_ICON_BY_CARD_NAME, "class", panelName).contains("fa-plus")) {
			waitForElementClickable(driver, AdminProductDetailPageUI.TOGGLE_ICON_BY_CARD_NAME, panelName);
			clickToElement(driver, AdminProductDetailPageUI.TOGGLE_ICON_BY_CARD_NAME, panelName);
		}
	}

	public boolean isPictureLoadedSuccesfully(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisible(driver, AdminProductDetailPageUI.PICTURE_CONTAINER_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, AdminProductDetailPageUI.PICTURE_CONTAINER_BY_FILE_NAME, fileName);
	}

	public boolean inputToPicturePanelTextboxe(String fileName) {
		return isElementDisplayed(driver, AdminProductDetailPageUI.PICTURE_CONTAINER_BY_FILE_NAME, fileName);
	}

	public void inputToAltTextbox(String textValue) {
		waitForElementVisible(driver, AdminProductDetailPageUI.ALT_TEXTBOX);
		sendkeyToElement(driver, AdminProductDetailPageUI.ALT_TEXTBOX, textValue);
	}

	public void inputToTitleTextbox(String textValue) {
		waitForElementVisible(driver, AdminProductDetailPageUI.TITLE_TEXTBOX);
		sendkeyToElement(driver, AdminProductDetailPageUI.TITLE_TEXTBOX, textValue);
	}

	public void inputToDisplayOrderTextbox(String typeBtn) {
		waitForElementClickable(driver, AdminProductDetailPageUI.DISPLAY_ORDER_TEXTBOX, typeBtn);
		clickToElement(driver, AdminProductDetailPageUI.DISPLAY_ORDER_TEXTBOX, typeBtn);
	}

	public void clickToAddProductPicture() {
		waitForElementClickable(driver, AdminProductDetailPageUI.ADD_PICTURE_BUTTON);
		clickToElement(driver, AdminProductDetailPageUI.ADD_PICTURE_BUTTON);
	}

	public boolean isPictureDisplayed(String fileName, String displayOrder, String alt, String title) {
		fileName = fileName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, AdminProductDetailPageUI.PICTURE_ROW_INFO, fileName, displayOrder, alt, title);
		return isElementDisplayed(driver, AdminProductDetailPageUI.PICTURE_ROW_INFO, fileName, displayOrder, alt, title);
	}

	public AdminProductSearchPageObject clickToSaveButton() {
		waitForElementClickable(driver, AdminProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminProductDetailPageUI.SAVE_BUTTON);
		return PageGeneratorManager.getAdminProductSearchPage(driver);
	}

	public void clickToDeleteButton(String productTitle) {
		waitForElementClickable(driver, AdminProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		clickToElement(driver, AdminProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		acceptAlert(driver);
	}
}
