package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MobileRcgPage extends BaseElement {

	WebDriver driver;

	public MobileRcgPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//nav[@class='nav header__nav']//a[.='Home']")
	private WebElement homeMenu;
	@FindBy(how = How.ID, using = "number_1")
	private WebElement number1;
	@FindBy(how = How.ID, using = "amount_1")
	private WebElement amount_1;
	@FindBy(how = How.ID, using = "email")
	private WebElement email;

	@FindBy(how = How.CSS, using = ".hide-medium-only > button.recharge__action__cta")
	private WebElement rechargeYourAccountButton;

	public void clickOnHomeMenu() {
		homeMenu.click();
		waitUntilToBeVisible(driver, rechargeYourAccountButton);
		Assert.assertTrue(rechargeYourAccountButton.isDisplayed());

	}

	public void insertNumber1(String numberOne) {
		number1.clear();
		number1.sendKeys(numberOne);
	}

	public void insertAmount1(String amountOne) {
		amount_1.clear();
		amount_1.sendKeys(amountOne);
	}

	public void insertEmail(String e_mail) {
		email.clear();
		email.sendKeys(e_mail);
	}

	public void clickOnrechargeYourAccountButton() {
		rechargeYourAccountButton.click();
	}

}
