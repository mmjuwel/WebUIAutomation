package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {

	public WebDriverWait wait;

	public void waitUntilToBeVisible(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf((element)));

	}

	public void waitUntilToBeInVisible(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf((element)));

	}

	public void waitUntilLoadingComplete(WebDriver driver) {
		wait = new WebDriverWait(driver, 60);
		WebElement loading = driver.findElement(By.cssSelector("div.c-loading>i"));
		wait.until(ExpectedConditions.invisibilityOf((loading)));

	}

}
