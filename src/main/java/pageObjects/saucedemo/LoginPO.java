package pageObjects.saucedemo;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.saucedemo.LoginPageUI;

public class LoginPO extends BasePage {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUserNameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public InventoryPO clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGenerator.getInventoryPage(driver);
	}
}
