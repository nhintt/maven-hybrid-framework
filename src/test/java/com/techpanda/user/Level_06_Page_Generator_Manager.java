package com.techpanda.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.techpanda.HomePageObject;
import pageObjects.techpanda.LoginPageObject;
import pageObjects.techpanda.MyAccountPageObject;
import pageObjects.techpanda.PageGeneratorManager;
import pageObjects.techpanda.RegisterPageObject;

public class Level_06_Page_Generator_Manager extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickToMyAccountLink();

		registerPage = loginPage.clickToCreateAccountButton();
		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("111111");
		registerPage.inputToConfirmPasswordTextbox("111111");

		myAccountPage = registerPage.clickToRegisterButton();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private String emailAddress = "afc" + generateNumber() + "@mail.vn";
}
