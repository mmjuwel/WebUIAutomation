package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.BaseTest;
import pages.SignInPage;
import utility.ExcelDataReader;

@Listeners(utility.Listener.class)
public class SignInTest extends BaseTest {

	SignInPage signIn = PageFactory.initElements(driver, SignInPage.class);

	@DataProvider(name = "SignIn")
	public Object[][] getData() throws Exception {
		Object data[][] = ExcelDataReader.getDataFromExcel("MyBl.xlsx", "SignIn");
		return data;
	}

	@Test(enabled = true, priority = 0, dataProvider = "SignIn", groups = { "regression" })
	public void signIn(String mobileNumber, String otp1, String otp2, String otp3, String otp4, String otp5,
			String otp6) throws Exception {

		testLogger = report.createTest("Test Case: MyBL SignIn.");

		signIn.clickOnMyAccount();
		testLogger.info("Click on My Account Menu");

		signIn.clickOnSignIn();
		testLogger.info("Click On Sign In");

		signIn.InsertMobileNumber(mobileNumber);
		testLogger.info("Insert Mobile Number:" + mobileNumber);

		signIn.clickOnCurculeArroButton();
		testLogger.info("Click on Curcule Arrow Button");

		signIn.insertOtp(otp1, otp2, otp3, otp4, otp5, otp6);
		testLogger.info("Insert OTP: " + otp1 + otp2 + otp3 + otp4 + otp5 + otp6);

		signIn.clickOnContinueButton();
		testLogger.info("Click on Continue Button ");

	}
}
