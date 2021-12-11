package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageObject extends BasePageFactory {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement registerLink;

	@FindBy(css = "a[class='ico-login']")
	private WebElement loginLink;

	@FindBy(xpath = "//a[@class='ico-account']")
	private WebElement myAccountLink;

	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;

	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, myAccountLink);
		return isElementDisplayed(driver, myAccountLink);
	}

	public boolean isLogoutLinkDisplayed() {
		waitForElementVisible(driver, logoutLink);
		return isElementDisplayed(driver, logoutLink);
	}

}
