package pageObjects.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.RegisterPageUI;

public class RegisterPageObjects extends BasePage {
	WebDriver driver;

	public RegisterPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public boolean isLoginButtonDisplayed() {
		boolean status = true;
		try {
			if (driver.findElement(By.xpath(RegisterPageUI.LOGIN_BUTTON)).isDisplayed()) {
				return status;
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public boolean isLoginButtonUndisplayed() {
		waitForElementInvisible(driver, RegisterPageUI.LOGIN_BUTTON);
		return isElementUndisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}

	public boolean isEmailTextboxDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_TEXTBOX);
	}
}
