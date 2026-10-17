package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import project.base.BasePage;

import project.utils.ElementWaits;

public class CheckoutOnePage extends BasePage {

	// Constructor
	public CheckoutOnePage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By firstNameInput = By.xpath("//input[@id='first-name']");
	private By lastNameInput = By.xpath("//input[@id='last-name']");
	private By zipCodeInput = By.xpath("//input[@id='postal-code']");
	private By continueButton = By.xpath("//input[@id='continue']");

	// Page Actions
	public void enterFirstName(String firstName) {

		ElementWaits.waitForVisibility(driver, firstNameInput).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {

		ElementWaits.waitForVisibility(driver, lastNameInput).sendKeys(lastName);
	}

	public void enterZipCode(String zipCode) {

		ElementWaits.waitForVisibility(driver, zipCodeInput).sendKeys(zipCode);
	}

	public void clickContinue() {

		ElementWaits.waitForVisibility(driver, continueButton).click();
	}

	public String getErrorMessage() {

		if (driver.findElements(errorMessage).isEmpty())
			return "";
		else
			return driver.findElement(errorMessage).getText();
	}

	// Page Behavior
	public void completeCheckoutInformation(String firstName, String lastName, String zipCode) {

		enterFirstName(firstName);
		enterLastName(lastName);
		enterZipCode(zipCode);
		clickContinue();

	}
}
