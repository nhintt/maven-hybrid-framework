package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.user.MyAccountPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNewsLetterSelected() {
		return isElementSelected(driver, MyAccountPageUI.NEWS_LETTER_CHECKBOX);
	}
}
