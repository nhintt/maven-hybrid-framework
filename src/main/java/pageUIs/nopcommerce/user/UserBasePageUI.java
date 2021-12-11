package pageUIs.nopcommerce.user;

public class UserBasePageUI {
	public static final String ADDRESS_LINK = "//div[contains(@class, 'account-navigation')]//a[text()='Addresses']";
	public static final String ORDER_LINK = "//li[contains(@class, 'customer-orders')]/a";
	public static final String REWARD_POINTS_LINK = "//li[contains(@class, 'reward-points')]/a";
	public static final String CUSTOMER_INFO_LINK = "//li[contains(@class, 'customer-info')]/a";
	public static final String LOGOUT_LINK_AT_USER = "//a[@class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "//a[text()='Logout']";

	// DYNAMIC LOCATOR
	public static final String DYNAMIC_PAGE_FOOTER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_HEADER = "//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
}
