package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Level_14_Close_Browser_Driver extends BaseTest {
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");

		// Thread.sleep(5000);
		Assert.assertTrue(false);
	}

	@Test
	public void User_01_Register() {

	}

	@Test
	public void User_02_Dynamic_Locator() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		// closeBrowserAndDriver();
	}

	private WebDriver driver;
}
