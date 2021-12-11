// package com.nopcommerce.user;
//
// import org.testng.annotations.Test;
//
// import commons.BasePage;
//
// import org.testng.annotations.BeforeClass;
//
// import java.util.Random;
// import java.util.concurrent.TimeUnit;
//
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
// import org.testng.Assert;
// import org.testng.annotations.AfterClass;
//
// public class Level_02_Apply_BasePage_I {
// String projectPath = System.getProperty("user.dir");
// WebDriver driver;
// String emailAddress;
// BasePage basePage;
//
// @BeforeClass
// public void beforeClass() {
// System.setProperty("webdriver.gecko.driver", projectPath + "//browserDrivers//geckodriver.exe");
// driver = new FirefoxDriver();
// basePage = new BasePage();
// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
// driver.manage().window().maximize();
//
// emailAddress = "afc" + generateNumber() + "@mail.vn";
// driver.get("https://demo.nopcommerce.com/");
// }
//
// @Test
// public void TC_01_Register_With_Empty_Data() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
// }
//
// @Test
// public void TC_02_Register_With_Invalid_Email() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
// basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
// basePage.sendkeyToElement(driver, "//input[@id='Email']", "123@(*&");
// basePage.sendkeyToElement(driver, "//input[@id='Password']", "111111");
// basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "111111");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
// }
//
// @Test
// public void TC_03_Register_With_Valid_Data() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
// basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
// basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
// basePage.sendkeyToElement(driver, "//input[@id='Password']", "111111");
// basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "111111");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
// Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
//
// basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
// basePage.clickToElement(driver, "//a[@class='ico-logout']");
// }
//
// @Test
// public void TC_04_Register_With_Exist_Data() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
// basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
// basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
// basePage.sendkeyToElement(driver, "//input[@id='Password']", "111111");
// basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "111111");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//div[(contains(@class, 'validation-summary-errors'))]//li"), "The specified email
// already exists");
// }
//
// @Test
// public void TC_05_Register_With_Pwd_Less_Than_6_Chars() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
// basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
// basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
// basePage.sendkeyToElement(driver, "//input[@id='Password']", "1111");
// basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "111111");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have
// at least 6 characters");
// }
//
// @Test
// public void TC_06_Register_With_CPwd_Not_Match_With_Pwd() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
// basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
// basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
// basePage.sendkeyToElement(driver, "//input[@id='Password']", "111221");
// basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "111111");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do
// not match.");
// }
//
// @AfterClass
// public void afterClass() {
// driver.quit();
// }
//
// public int generateNumber() {
// Random random = new Random();
// return random.nextInt(9999);
// }
// }
