
package project.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.base.DriverFactory;
import project.expected.CheckoutCompleteExpected;
import project.pages.CartPage;
import project.pages.CheckoutCompletePage;
import project.pages.CheckoutTwoPage;

public class PurchaseSteps {

	private WebDriver driver;

	private CartPage cartPage;
	private CheckoutTwoPage checkoutTwoPage;
	private CheckoutCompletePage checkoutComplete;

	private List<String> expectedProductNames;
	private List<String> expectedProductPrices;
	private int expectedProductCount;

	public PurchaseSteps() {
		driver = DriverFactory.getDriver();
		cartPage = new CartPage(driver);
		checkoutTwoPage = new CheckoutTwoPage(driver);
		checkoutComplete = new CheckoutCompletePage(driver);
	}

	@When("the user records the cart product details")
	public void theUserRecordsTheCartProductDetails() {
		expectedProductNames = new ArrayList<>(cartPage.getCartProductNames());
		expectedProductPrices = new ArrayList<>(cartPage.getCartProductPrices());
		expectedProductCount = cartPage.getCartProductCount();
	}

	@Then("the checkout overview should contain the expected product details")
	public void theCheckoutOverviewShouldContainExpectedProductDetails() {
		Assertions.assertEquals(expectedProductNames, checkoutTwoPage.getAllProductNames());
		Assertions.assertEquals(expectedProductPrices, checkoutTwoPage.getAllProductPrices());
		Assertions.assertEquals(expectedProductCount, checkoutTwoPage.getCheckoutProductCount());
	}

	@Then("the checkout overview should display the correct total price")
	public void theCheckoutOverviewShouldDisplayTheCorrectTotalPrice() {
		Assertions.assertEquals(checkoutTwoPage.calculateExpectedTotalPrice(), checkoutTwoPage.getTotalPriceCharged());
	}

	@When("the user completes the purchase")
	public void theUserCompletesThePurchase() {
		checkoutTwoPage.clickFinish();
	}

	@Then("the purchase confirmation icon should be visible")
	public void thePurchaseConfirmationIconShouldBeVisible() {
		Assertions.assertTrue(checkoutComplete.isConfirmationIconVisible());
	}

	@Then("the purchase confirmation title should be displayed")
	public void thePurchaseConfirmationTitleShouldBeDisplayed() {
		Assertions.assertEquals(CheckoutCompleteExpected.CONFIRMATION_HEADER, checkoutComplete.getConfirmationTitle());
	}

	@Then("the purchase confirmation message should be displayed")
	public void thePurchaseConfirmationMessageShouldBeDisplayed() {
		Assertions.assertEquals(CheckoutCompleteExpected.CONFIRMATION_DESCRIPTION,checkoutComplete.getConfirmationMessage());
	}
}
