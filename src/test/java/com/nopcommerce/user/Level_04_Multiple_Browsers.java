package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.Parameters;

public class Level_04_Multiple_Browsers extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		homePage = new UserHomePageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_With_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_02: - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@@#$");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_02: - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02: - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
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
