package project.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import project.base.BasePage;
import project.utils.ElementWaits;

public class CartPage extends BasePage {

	// Constructor
	public CartPage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By firstProductName = By.xpath("//div[@data-test='inventory-item-name']");
	private By firstProductPrice = By.xpath("//div[@data-test='inventory-item-price']");
	private By firstProductRemoveButton = By.xpath("(//button[contains(@name,'remove-sauce')]");
	private By numberOfProducts = By.xpath("//div[@data-test='inventory-item']");
	private By checkoutButton = By.xpath("//button[@id='checkout']");
	private By cartProductNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By cartProductPrices = By.xpath("//div[@data-test='inventory-item-price']");

	public By getRemoveBtnLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']/../..//button");
	}

	// Page Actions
	public String getProductName() {

		return ElementWaits.waitForVisibility(driver,firstProductName).getText();
	}

	public void removeProductByNameFromCart(String productName) {

		driver.findElement(getRemoveBtnLocator(productName)).click();
	}

	public List<String> getCartProductNames() {


		List<WebElement> productNameElements = driver.findElements(cartProductNames);
		List<String> productNameString = new ArrayList<>();

		for (WebElement e : productNameElements) {
			productNameString.add(e.getText());
		}


		return productNameString;
	}

	public List<String> getCartProductPrices() {


		List<WebElement> selectedProductNamesElements = driver.findElements(cartProductPrices);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) {
			selectedProductNamesString.add(e.getText());
		}


		return selectedProductNamesString;
	}

	public String getFirstProductPrice() {

		return ElementWaits.waitForVisibility(driver,firstProductPrice).getText();
	}

	public void removeFirstItem() {

		ElementWaits.waitForVisibility(driver,firstProductRemoveButton).click();
	}

	public void clickCheckout() {

		ElementWaits.waitForVisibility(driver,checkoutButton).click();
	}

	public int getCartProductCount() {

		return driver.findElements(numberOfProducts).size();
	}

	public int getCartBadgeCount() {


		String value = driver.findElement(shoppingCartIcon).getText();

		if (value.isEmpty()) {
			return 0;
		} else {
			int cartCount = Integer.parseInt(value);


			return cartCount;
		}
	}

}
