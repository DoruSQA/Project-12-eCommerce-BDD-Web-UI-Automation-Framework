package project.steps;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.base.DriverFactory;
import project.expected.CartExpected;
import project.pages.CartPage;
import project.pages.InventoryPage;

public class CartSteps {

	private WebDriver driver;
	private InventoryPage inventoryPage;
	private CartPage cartPage;

	private String expectedProductName;
	private String expectedProductPrice;

	private List<String> expectedProductNames;
	private List<String> expectedProductPrices;

	private int expectedBadgeCount;
	private int initialCartBadgeCount;
	private int initialCartProductCount;

	public CartSteps() {
		driver = DriverFactory.getDriver();

		inventoryPage = new InventoryPage(driver);
		cartPage = new CartPage(driver);
	}

	// Scenario 1: Add one product to the cart
	@When("the user adds the {string} to the cart")
	public void theUserAddsProductToTheCart(String productName) {
		inventoryPage.addProductToCart(productName);

		expectedProductName = inventoryPage.getAddedProductName();
		expectedProductPrice = inventoryPage.getAddedProductPrice();

		expectedBadgeCount = inventoryPage.getAddedProductCount();
	}

	@Then("the cart badge should display the number of selected products")
	public void theCartBadgeShouldDisplayTheNumberOfSelectedProducts() {
		Assertions.assertEquals(expectedBadgeCount, inventoryPage.getCartBadgeIconCount());
	}

	@When("the user opens the shopping cart")
	public void theUserOpensTheShoppingCart() {
		inventoryPage.clickCartBadgeIcon();
	}

	@Then("the cart page heading should be displayed")
	public void theCartPageHeadingShouldBeDisplayed() {
		Assertions.assertEquals(CartExpected.PAGE_HEADING, cartPage.getPageHeading());
	}

	@And("the cart page URL should be correct")
	public void theCartPageUrlShouldBeCorrect() {
		Assertions.assertEquals(CartExpected.URL, cartPage.getUrl());
	}

	@And("the cart should contain the selected product")
	public void theCartShouldContainTheSelectedProduct() {
		Assertions.assertEquals(expectedProductName, cartPage.getProductName());
	}

	@And("the cart should display the correct product price")
	public void theCartShouldDisplayTheCorrectProductPrice() {
		Assertions.assertEquals(expectedProductPrice, cartPage.getFirstProductPrice());
	}

	// Scenario 2: Add multiple products to the cart
	@When("the user adds the following products to the cart:")
	public void theUserAddsTheFollowingProductsToTheCart(List<String> productNames) {
		inventoryPage.addMultipleProductsToTheCart(productNames);

		expectedProductNames = inventoryPage.getAddedProductNameList();
		expectedProductPrices = inventoryPage.getAddedProductPriceList();

		expectedBadgeCount = expectedProductNames.size();
	}

	@And("the cart should contain all selected products")
	public void theCartShouldContainAllSelectedProducts() {
		Assertions.assertEquals(expectedProductNames, cartPage.getCartProductNames());
	}

	@And("the cart should display the correct prices for all selected products")
	public void theCartShouldDisplayTheCorrectPricesForAllSelectedProducts() {
		Assertions.assertEquals(expectedProductPrices, cartPage.getCartProductPrices(),
				"The product prices displayed in the cart are incorrect.");
	}

	// Scenario 3: Remove a product from the cart
	@When("the user removes the {string} from the cart")
	public void theUserRemovesProductFromTheCart(String productName) {
		initialCartBadgeCount = cartPage.getCartBadgeCount();
		initialCartProductCount = cartPage.getCartProductCount();

		cartPage.removeProductByNameFromCart(productName);
	}

	@Then("the cart badge count should decrease by one")
	public void theCartBadgeCountShouldDecreaseByOne() {
		Assertions.assertEquals(initialCartBadgeCount - 1, cartPage.getCartBadgeCount());
	}

	@And("the number of products in the cart should decrease by one")
	public void theNumberOfProductsInTheCartShouldDecreaseByOne() {
		Assertions.assertEquals(initialCartProductCount - 1, cartPage.getCartProductCount());
	}
}
