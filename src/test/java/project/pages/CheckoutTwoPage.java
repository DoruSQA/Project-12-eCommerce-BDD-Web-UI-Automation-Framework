package project.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import project.base.BasePage;
import project.utils.ElementWaits;

public class CheckoutTwoPage extends BasePage {


	// Constructor
	public CheckoutTwoPage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By checkoutProductNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By checkoutProductPrices = By.xpath("//div[@data-test='inventory-item-price']");
	private By finishButton = By.xpath("//button[@id='finish']");
	private By numberOfProducts = By.xpath("//div[@class='cart_item']");
	private By totalPriceCharged = By.xpath("//div[@data-test='total-label']");
	private By additionalTax = By.xpath("//div[@data-test='tax-label']");

	// Page Actions
	public List<String> getAllProductNames() {

		List<WebElement> productElements = driver.findElements(checkoutProductNames);
		List<String> productString = new ArrayList<>();

		for (WebElement product : productElements) {
			productString.add(product.getText());
		}

		return productString;
	}

	public String getProductName() {

		return getAllProductNames().get(0); 
	}

	public List<String> getAllProductPrices() {

		List<WebElement> productElements = driver.findElements(checkoutProductPrices);
		List<String> productPrices = new ArrayList<>();

		for (WebElement product : productElements) 
			productPrices.add(product.getText());
		

		return productPrices;
	}

	public String getProductPrice() {

		String productPrice = getAllProductPrices().get(0);

		return productPrice;
	}

	public void clickFinish() {

		ElementWaits.waitForVisibility(driver,finishButton).click();
	}

	public double getTotalPriceCharged() {

		String price = ElementWaits.waitForVisibility(driver,totalPriceCharged).getText(); // Total: $43.18
		double totalPrice = Double.parseDouble(price.replaceAll("[^0-9.]", ""));

		return totalPrice;
	}

	public double getAdditionalTaxCharged() {

		String price = ElementWaits.waitForVisibility(driver,additionalTax).getText(); // Tax: 5$
		double additionalTaxValue = Double.parseDouble(price.replaceAll("[^0-9.]", ""));

		return additionalTaxValue;
	}

	public int getCheckoutProductCount() {

		return driver.findElements(numberOfProducts).size();
	}

	public List<Double> parseProductPrices(List<String> products) {

		List<Double> productPrices = new ArrayList<>();

		for (String product : products) 
			productPrices.add(Double.parseDouble(product.replaceAll("[^0-9.]", "")));

		return productPrices;
	}

	// Page Behavior
	public double calculateExpectedTotalPrice() {

		double total = 0;
		List<Double> productPrices = parseProductPrices(getAllProductPrices());

		for (Double item : productPrices) {
			total += item;
		}

		total += getAdditionalTaxCharged();

		return total;
	}
}
