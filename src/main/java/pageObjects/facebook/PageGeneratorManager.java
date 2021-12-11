package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static RegisterPageObjects getRegisterPage(WebDriver driver) {
		return new RegisterPageObjects(driver);
	}
}
