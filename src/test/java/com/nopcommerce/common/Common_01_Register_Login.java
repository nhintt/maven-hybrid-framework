package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

public class Common_01_Register_Login extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);

		userEmailAddress = "afc" + generateNumber() + "@mail.vn";
		userPassword = "123456";

		log.info("User_01_Register - Step 01: Open 'Home Page'");
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("User_01_Register - Step 02: Click to 'Register Link'");
		userRegisterPage = userHomePage.clickToRegisterLink();

		log.info("User_01_Register - Step 03: Input to Firstname textbox");
		userRegisterPage.inputToFirstnameTextbox("John");

		log.info("User_01_Register - Step 04: Input to Lastname textbox");
		userRegisterPage.inputToLastnameTextbox("Terry");

		log.info("User_01_Register - Step 05: Input to Email textbox");
		userRegisterPage.inputToEmailTextbox(userEmailAddress);

		log.info("User_01_Register - Step 06: Input to Password textbox");
		userRegisterPage.inputToPasswordTextbox(userPassword);

		log.info("User_01_Register - Step 07: Input to ConfirmPassword textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

		log.info("User_01_Register - Step 08: Click to Register button");
		userRegisterPage.clickToRegisterButton();

		log.info("User_01_Register - Step 09: Verify successful message display");
		verifyTrue(userRegisterPage.isRegisterSuccessMessageDisplayed());

		log.info("User_01_Register - Step 10: Click to Logout Link");
		userHomePage.clickToLogoutLinkAtUserPage(driver);

		log.info("User_01_Register - Step 11: Verify Login Link display");
		verifyTrue(userHomePage.isLoginLinkDisplayed());

		log.info("User_02_Register - Step 01: Open Login page");
		userLoginPage = userHomePage.openLoginPage();

		log.info("User_02_Register - Step 02: Login successfully");
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		log.info("User_02_Register - Step 03: Verify Logout Link display");
		verifyTrue(userHomePage.isLogoutLinkDisplayed());

		loginPageCookie = userHomePage.getCookies(driver);

		log.info("Post-Condition: Close browser");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserRegisterPageObject userRegisterPage;
	private String userEmailAddress, userPassword;
	public static Set<Cookie> loginPageCookie;
}
