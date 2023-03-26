package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInPage extends BaseElement {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "My account")
	private WebElement myAccount;

	@FindBy(how = How.CSS, using = "[role='button'].nav__list__item__link")
	private WebElement signIn;

	@FindBy(how = How.ID, using = "number")
	private WebElement number;

	@FindBy(how = How.CSS, using = "svg[width='7'][height='11']")
	private WebElement CurculeArroButton;

	@FindBy(how = How.ID, using = "number_1")
	private WebElement OTPDigit1;

	@FindBy(how = How.ID, using = "number_2")
	private WebElement OTPDigit2;

	@FindBy(how = How.ID, using = "number_3")
	private WebElement OTPDigit3;

	@FindBy(how = How.ID, using = "number_4")
	private WebElement OTPDigit4;

	@FindBy(how = How.ID, using = "number_5")
	private WebElement OTPDigit5;
	@FindBy(how = How.ID, using = "number_6")
	private WebElement OTPDigit6;

	@FindBy(how = How.CSS, using = "button[type='submit']")
	private WebElement continueButton;

	public void clickOnMyAccount() {
		myAccount.click();
		waitUntilToBeVisible(driver, signIn);
	}

	public void clickOnSignIn() {
		waitUntilToBeVisible(driver, signIn);
		signIn.click();
		Assert.assertTrue(number.isDisplayed());
		waitUntilToBeVisible(driver, number);
	}

	public void InsertMobileNumber(String MobileNumber) {
		number.clear();
		number.sendKeys(MobileNumber);
	}

	public void clickOnCurculeArroButton() {
		CurculeArroButton.click();
		waitUntilToBeVisible(driver, continueButton);
		Assert.assertTrue(continueButton.isDisplayed());
	}

	public void insertOtp(String otp1, String otp2, String otp3, String otp4, String otp5, String otp6) {
		OTPDigit1.sendKeys(otp1);
		OTPDigit2.sendKeys(otp2);
		OTPDigit3.sendKeys(otp3);
		OTPDigit4.sendKeys(otp4);
		OTPDigit5.sendKeys(otp5);
		OTPDigit6.sendKeys(otp6);

	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

}
