package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;
//import reportConfig.ExtentTestManager;

public class Level_13_Register_Login_Extent_Report extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);

		userEmailAddress = "afc" + generateNumber() + "@mail.vn";
		userPassword = "123456";
	}

	@Test
	public void User_01_Register(Method method) {
		// ExtentTestManager.startTest(method.getName(), "User_01_Register");
		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 01: Open 'Home Page'");
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 02: Click to 'Register Link'");
		userRegisterPage = userHomePage.clickToRegisterLink();

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 03: Input to Firstname textbox");
		userRegisterPage.inputToFirstnameTextbox("John");

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 04: Input to Lastname textbox");
		userRegisterPage.inputToLastnameTextbox("Terry");

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 05: Input to Email textbox");
		userRegisterPage.inputToEmailTextbox(userEmailAddress);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 06: Input to Password textbox");
		userRegisterPage.inputToPasswordTextbox(userPassword);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 07: Input to ConfirmPassword textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 08: Click to Register button");
		userRegisterPage.clickToRegisterButton();

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 09: Verify successful message display");
		verifyTrue(userRegisterPage.isRegisterSuccessMessageDisplayed());

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 10: Click to Logout Link");
		userHomePage.clickToLogoutLinkAtUserPage(driver);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 11: Verify Login Link display");
		verifyFalse(userHomePage.isLoginLinkDisplayed());

		// ExtentTestManager.endTest();
	}

	@Test
	public void User_02_Dynamic_Locator(Method method) {
		// ExtentTestManager.startTest(method.getName(), "User_02_Dynamic_Locator");
		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register - Step 01: Open Login page");
		userLoginPage = userHomePage.openLoginPage();

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register - Step 02: Login successfully");
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		// ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register - Step 03: Verify Logout Link display");
		verifyTrue(userHomePage.isLogoutLinkDisplayed());

		// ExtentTestManager.endTest();
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserRegisterPageObject userRegisterPage;
	private String userEmailAddress, userPassword;
}
