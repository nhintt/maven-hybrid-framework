package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalContants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import pageObjects.hrm.MyInfoPO;

public class Level_17_Live_Coding extends BaseTest {
	String employeeID, statusValue, empFirstName, empLastName, empUserName, empPassword, empFullName, adminUsername, adminPassword, imagePath;
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		adminUsername = "Admin";
		adminPassword = "admin123";
		empFirstName = "Johny";
		empLastName = "Frash";
		empUserName = "johny";
		empPassword = "12345678";
		empFullName = empFirstName + " " + empLastName;
		imagePath = GlobalContants.UPLOAD_FOLDER_PATH + "avatar.png";

		editEmpFirstName = "Harry";
		editEmpLastName = "Potter";
		editEmpGender = "Male";
		editEmpMaritalStatus = "Single";
		editEmpNationality = "Vietnamese";

		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login with 'Admin Role'");
		dashboardPage = loginPage.loginToSystem(driver, adminUsername, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01_Add_New_Employee - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Employee_01_Add_New_Employee - Step 03: Input valid info to 'First Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "firstName", empFirstName);

		log.info("Employee_01_Add_New_Employee - Step 04: Input valid info to 'Last Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "lastName", empLastName);

		log.info("Employee_01_Add_New_Employee - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Employee_01_Add_New_Employee - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Employee_01_Add_New_Employee - Step 07: Input valid info to 'User Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_name", empUserName);

		log.info("Employee_01_Add_New_Employee - Step 08: Input valid info to 'Password' textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_password", empPassword);

		log.info("Employee_01_Add_New_Employee - Step 09: Input valid info to 'Confirm Password' textbox");
		addEmployeePage.inputToTextboxByID(driver, "re_password", empPassword);

		log.info("Employee_01_Add_New_Employee - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		employeeListPage.selectToDropdownByID(driver, "status", "Enabled");

		log.info("Employee_01_Add_New_Employee - Step 11: Click to 'Save' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 12: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 13: Input valid Employee Name to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.inputToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01_Add_New_Employee - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01_Add_New_Employee - Step 15: Verify employee info in 'Result table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Employee_02_Upload_Avatar - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);

		log.info("Employee_02_Upload_Avatar - Step 02: Open 'My info' page");
		dashboardPage.openMenuPageByText(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Employee_02_Upload_Avatar - Step 03: Click to change Avater image");
		myInfoPage.clickToChangeAvatarImg();

		log.info("Employee_02_Upload_Avatar - Step 04: Upload Avatar image");
		myInfoPage.uploadImage(driver, imagePath);

		log.info("Employee_02_Upload_Avatar - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_02_Upload_Avatar - Step 06: Verify upload success message");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Employee_02_Upload_Avatar - Step 07: Verify new Avatar image is displayed");
		verifyTrue(myInfoPage.isNewAvatarDisplayed());
	}

	@Test
	public void Employee_03_Personal_Detail() {
		log.info("Employee_03_Personal_Detail - Step 01: Open 'Personal Details' page");
		myInfoPage.openSidebarPageByName(driver, "Personal Details");

		log.info("Employee_03_Personal_Detail - Step 02: Verify all fields at 'Personal Detail' page are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));

		log.info("Employee_03_Personal_Detail - Step 03: Click to Edit button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_03_Personal_Detail - Step 04: Verify 'Employee ID' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));

		log.info("Employee_03_Personal_Detail - Step 05: Verify 'Driver's License Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));

		log.info("Employee_03_Personal_Detail - Step 06: Verify 'SSN Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));

		log.info("Employee_03_Personal_Detail - Step 07: Verify 'SIN Number' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));

		log.info("Employee_03_Personal_Detail - Step 08: Verify 'Date Of Birth' field is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));

		log.info("Employee_03_Personal_Detail - Step 09: Edit FirstName textbox");
		myInfoPage.inputToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);

		log.info("Employee_03_Personal_Detail - Step 10: Edit LastName textbox");
		myInfoPage.inputToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);

		log.info("Employee_03_Personal_Detail - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);

		log.info("Employee_03_Personal_Detail - Step 12: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectToDropdownByID(driver, "personal_cmbMarital", "Single");

		log.info("Employee_03_Personal_Detail - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.selectToDropdownByID(driver, "personal_cmbNation", "Vietnamese");

		log.info("Employee_03_Personal_Detail - Step 14: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_03_Personal_Detail - Step 15: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Employee_03_Personal_Detail - Step 16: Verify 'First Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		log.info("Employee_03_Personal_Detail - Step 17: Verify 'Last Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		log.info("Employee_03_Personal_Detail - Step 18: Verify 'Gender' radio button is updated success");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLabel(driver, editEmpGender));

		log.info("Employee_03_Personal_Detail - Step 19: Verify 'Marital Status' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);

		log.info("Employee_03_Personal_Detail - Step 20: Verify 'Nationality' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);
	}

	@Test
	public void Employee_04_Contact_Detail() {

	}

	@Test
	public void Employee_05_Emergency_Contact() {

	}

	@Test
	public void Employee_06_Assigned_Dependents() {

	}

	@Test
	public void Employee_07_Edit_View_Job() {

	}

	@Test
	public void Employee_08_Edit_View_Salary() {

	}

	@Test
	public void Employee_09_Edit_View_Tax() {

	}

	@Test
	public void Employee_10_Qualifications() {

	}

	@Test
	public void Employee_11_Search_Employee() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	private AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
}
