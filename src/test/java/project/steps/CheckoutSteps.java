package project.steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.base.DriverFactory;
import project.expected.CheckoutOneExpected;
import project.pages.CartPage;
import project.pages.CheckoutOnePage;

public class CheckoutSteps {

	private WebDriver driver;
	private CartPage cartPage;
	private CheckoutOnePage checkoutOnePage;

	public CheckoutSteps() {
		driver = DriverFactory.getDriver();

		cartPage = new CartPage(driver);
		checkoutOnePage = new CheckoutOnePage(driver);
	}

	@When("the user proceeds to checkout")
	public void theUserProceedsToCheckout() {
		cartPage.clickCheckout();
	}

	@Then("the checkout information page should be displayed")
	public void theCheckoutInformationPageShouldBeDisplayed() {
		Assertions.assertEquals(CheckoutOneExpected.URL, checkoutOnePage.getUrl());
		Assertions.assertEquals(CheckoutOneExpected.PAGE_HEADING, checkoutOnePage.getPageHeading());
	}

	@When("the user submits checkout information with first name {string} last name {string} and ZIP code {string}")
	public void theUserSubmitsCheckoutInformation(String firstName, String lastName, String zipCode) {

		checkoutOnePage.completeCheckoutInformation(firstName, lastName, zipCode);
	}

	@Then("the checkout information page URL should remain unchanged")
	public void theCheckoutInformationPageUrlShouldRemainUnchanged() {
		Assertions.assertEquals(CheckoutOneExpected.URL, checkoutOnePage.getUrl());
	}

	@And("the checkout error message should indicate that the first name is required")
	public void theCheckoutErrorShouldIndicateFirstNameIsRequired() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_FIRSTNAME);
	}

	@And("the checkout error message should indicate that the last name is required")
	public void theCheckoutErrorShouldIndicateLastNameIsRequired() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_LASTNAME);
	}

	@And("the checkout error message should indicate that the ZIP code is required")
	public void theCheckoutErrorShouldIndicateZipCodeIsRequired() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_ZIPCODE);
	}

	@And("the checkout information page heading should be displayed")
	public void theCheckoutInformationPageHeadingShouldBeDisplayed() {
		Assertions.assertEquals(CheckoutOneExpected.PAGE_HEADING, checkoutOnePage.getPageHeading());
	}

	@And("the checkout error message should indicate that the first name is too long")
	public void theCheckoutErrorShouldIndicateFirstNameIsTooLong() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_FIRST_NAME);
	}

	@And("the checkout error message should indicate that the last name is too long")
	public void theCheckoutErrorShouldIndicateLastNameIsTooLong() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_LAST_NAME);
	}

	@And("the checkout error message should indicate that the ZIP code is too long")
	public void theCheckoutErrorShouldIndicateZipCodeIsTooLong() {
		assertErrorMessage(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_ZIP_CODE);
	}

	private void assertErrorMessage(String expectedMessage) {
		Assertions.assertEquals(expectedMessage, checkoutOnePage.getErrorMessage());
	}
}