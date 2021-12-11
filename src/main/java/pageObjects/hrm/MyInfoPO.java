package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPO extends BasePage {
	private WebDriver driver;

	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangeAvatarImg() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMG);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMG);
	}

	public boolean isNewAvatarDisplayed() {
		int imageWidth = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMG, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMG, "height"));
		return (imageWidth != 200) || (imageHeight != 200);
	}

	public void openSidebarPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, MyInfoPageUI.SIDEBAR_PAGE_BY_NAME, pageName);
		clickToElement(driver, MyInfoPageUI.SIDEBAR_PAGE_BY_NAME, pageName);
	}
}
