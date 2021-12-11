package com.facebook;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.facebook.RegisterPageObjects;

public class Level_12_Register_Verify_Assert extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@Test
	public void Register_01_Assert() {
		log.info("I - M - H - E - R - E");
		// Failed lần 1
		verifyFalse(registerPage.isEmailTextboxDisplayed());

		registerPage.inputToEmailTextbox("nhi@gmail.com");

		// Failed lần 2
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());

		registerPage.inputToEmailTextbox("");
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());

		verifyTrue(registerPage.isConfirmEmailTextboxDisplayed());

		// Failed lần 3
		verifyFalse(registerPage.isLoginButtonDisplayed());

		// Expected:
		// Testcase result: failed
		// Log error message
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private RegisterPageObjects registerPage;
}
