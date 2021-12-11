package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_02_Login {
	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String firstName, lastName, notFoundEmail, invalidEmail, existingEmail, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//browserDrivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		existingEmail = "afc" + generateNumber() + "@mail.vn";
		notFoundEmail = "afc" + generateNumber() + "@mail.vn";
		invalidEmail = "afc@123#@.123";

		System.out.println("Pre-Condition - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);

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
		registerPage.clickToLogoutLink();
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 - Step 01: Click to Login link");
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login_01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click to Login link");
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

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
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

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
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

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
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

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
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login_04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login_04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();

		homePage = new UserHomePageObject(driver);

		System.out.println("Login_06 - Step 04: Verify login successfully");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
