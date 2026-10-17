package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import project.base.BasePage;
import project.utils.ElementWaits;


public class LoginPage extends BasePage {


	// Constructor
	public LoginPage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By usernameInput = By.xpath("//input[@id='user-name']");
	private By passwordInput = By.xpath("//input[@id='password']");
	private By loginButton = By.xpath("//input[@id='login-button']");

	// Page Actions
	public void enterUsername(String username) {
	    WebElement element = ElementWaits.waitForVisibility(driver, usernameInput);
	    element.clear();
	    element.sendKeys(username);
	}

	
	public void enterPassword(String password) {
	    WebElement element = ElementWaits.waitForVisibility(driver, passwordInput);
	    element.clear();
	    element.sendKeys(password);
	}
	
	public void clickLogin() {

		ElementWaits.waitForVisibility(driver, loginButton).click();
	}

	public boolean isLoginButtonVisible() {

		return ElementWaits.waitForVisibility(driver, loginButton).isDisplayed();
	}

	public void pressEnter() {

		ElementWaits.waitForVisibility(driver, passwordInput).sendKeys(Keys.ENTER);
	}

	public void navigateTo(String url) {

		driver.navigate().to(url);
	}

	public String getErrorMessage() {

		return  ElementWaits.waitForVisibility(driver, errorMessage).getText();
	}

	// Page Behavior
	public void authentication(String Username, String Password) {


		enterUsername(Username);
		enterPassword(Password);
		clickLogin();

	}

//	public void authentication(LoginUser user) {
//
//		enterUsername(user.getUsername());
//		enterPassword(user.getPassword());
//		clickLogin();
//
//	}
}
