package pageUIs.nopcommerce.admin;

public class AdminProductDetailPageUI {
	public static final String TOGGLE_ICON_BY_CARD_NAME = "//div[text()='%s']/following-sibling::div//i";
	public static final String PICTURE_CONTAINER_BY_FILE_NAME = "//div[@class='picture-container']//img[contains(@src, '%s')]";
	public static final String ALT_TEXTBOX = "//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String TITLE_TEXTBOX = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DISPLAY_ORDER_TEXTBOX = "//input[@id='AddPictureModel_DisplayOrder']/following-sibling::span/span[@aria-label='%s value']";
	public static final String ADD_PICTURE_BUTTON = "//button[@id='addProductPicture']";
	public static final String PICTURE_ROW_INFO = "//a[contains(@href, '%s')]/parent::td/following-sibling::td[@data-columnname='DisplayOrder' and text()='%s']/following-sibling::td[@data-columnname='OverrideAltAttribute' and text()='%s']/following-sibling::td[@data-columnname='OverrideTitleAttribute' and text()='%s']";
	public static final String SAVE_BUTTON = "//button[@name='save']";
	public static final String DELETE_BUTTON_BY_IMAGE_TITLE = "//td[text()='%s']/following-sibling::td/a[contains(string(), 'Delete')]";
}
