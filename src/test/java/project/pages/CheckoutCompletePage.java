package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import project.base.BasePage;
import project.utils.ElementWaits;

public class CheckoutCompletePage extends BasePage {


	// Constructor
	public CheckoutCompletePage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By confirmationImage = By.xpath("//img[@data-test='pony-express']");
	private By confirmationTitle = By.xpath("//h2[@data-test='complete-header']");
	private By confirmationMessage = By.xpath("//div[@data-test='complete-text']");

	// Page Actions
	public boolean isConfirmationIconVisible() {
		
		return  ElementWaits.waitForVisibility(driver,confirmationImage).isDisplayed();
	}

	public String getConfirmationTitle() {

		return  ElementWaits.waitForVisibility(driver,confirmationTitle).getText();
	}

	public String getConfirmationMessage() {

		return  ElementWaits.waitForVisibility(driver,confirmationMessage).getText();
	}
}
