package pageObjects.techpanda;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.techpanda.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToCreateAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_ACCOUNT_BUTTON);
		return PageGeneratorManager.getRegisterPageObject(driver);
	}

}
