package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Login;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

public class Level_15_Share_State_Of_Class extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("User_02_Register - Step 01: Open Login page");
		userLoginPage = userHomePage.openLoginPage();

		log.info("User_02_Register - Step 02: Login successfully");
		userLoginPage.setCookies(driver, Common_01_Register_Login.loginPageCookie);
		userLoginPage.sleepInSecond(5);
		userLoginPage.refreshCurrentPage(driver);

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("User_02_Register - Step 03: Verify Logout Link display");
		verifyTrue(userHomePage.isLogoutLinkDisplayed());

		userHomePage.openCustomerInfoPage();
	}

	@Test
	public void User_01_Register() {

	}

	@Test
	public void User_02_Register() {

	}

	@Test
	public void User_03_Register() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
}
