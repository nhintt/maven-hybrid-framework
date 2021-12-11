package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_Part_III extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		existingEmail = "afc" + generateNumber() + "@mail.vn";
		notFoundEmail = "afc" + generateNumber() + "@mail.vn";
		invalidEmail = "afc@123#@.123";

		System.out.println("Pre-Condition - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 04: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_02 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_NotFound_Email() {
		System.out.println("Login_03 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_03 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_03 - Step 04: Verify not found email error message displayed");
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		System.out.println("Login_04 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");

		System.out.println("Login_04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_04 - Step 04: Verify empty password error message displayed");
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Invalid_Password() {
		System.out.println("Login_05 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_05 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("123123");

		System.out.println("Login_05 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_05 - Step 04: Verify incorrect password error message displayed");
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Data() {
		System.out.println("Login_04 - Step 01: Click to Login link");
		loginPage = homePage.openLoginPage();

		System.out.println("Login_04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_04 - Step 03: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		System.out.println("Login_06 - Step 04: Verify login successfully");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());

		customerInfoPage = homePage.openCustomerInfoPage();
		Assert.assertTrue(customerInfoPage.isNewsLetterSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, notFoundEmail, invalidEmail, existingEmail, password;
}
