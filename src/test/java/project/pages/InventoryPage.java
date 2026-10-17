package project.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import project.base.BasePage;
import project.utils.ElementWaits;

public class InventoryPage extends BasePage {

	// Constructor
	public InventoryPage(WebDriver d) {
		super(d);
	}

	// Page Locators
	private By sortingDropdown = By.xpath("//select[@data-test='product-sort-container']");
	private By logoutButton = By.xpath("//a[@id='logout_sidebar_link']");
	private By shoppingCartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");
	private By inventoryItemNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By inventoryItemPrices = By.xpath("//div[@data-test='inventory-item-price']");
	private By productPrice = By.xpath("//*[text()='Remove']/preceding-sibling::div");
	private By productName = By.xpath("//*[text()='Remove']/../../descendant::div[@data-test='inventory-item-name']");
	private By nrOfProducts = By.xpath("//button[text()='Remove']");
	private By productNameList = By.xpath("//button[text()='Remove']/../..//a");
	private By productPriceList = By.xpath("//button[text()='Remove']/../div[@class='inventory_item_price']");

	public By getAddToCartButtonLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']/../../following-sibling::div/button");
	}

	public By getproductNameLabelLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']");
	}

	// Page Actions
	public void addProductByName(String productName) {

		ElementWaits.waitForVisibility(driver,getAddToCartButtonLocator(productName)).click();
	}

	public void addMultipleProductsToTheCart(List<String> products) {

		for (String p : products) 
			addProductByName(p);
		
	}

	public List<String> getAddedProductNameList() {


		List<WebElement> selectedProductNamesElements = driver.findElements(productNameList);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());
		

		return selectedProductNamesString;
	}

	public List<String> getAddedProductPriceList() {


		List<WebElement> selectedProductNamesElements = driver.findElements(productPriceList);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());


		return selectedProductNamesString;
	}

	public void clickOnProductByName(String productName) { // separare!

		driver.findElement(getproductNameLabelLocator(productName)).click();
	}

	public List<String> getAllProductNames() {


		List<WebElement> selectedProductNamesElements = driver.findElements(inventoryItemNames);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());


		return selectedProductNamesString;
	}

	public List<Double> getAllProductPrices() {


		List<WebElement> selectedProductNamesElements = driver.findElements(inventoryItemPrices);
		List<Double> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) {
			String price = e.getText();
			double priceParing = Double.parseDouble(price.replaceAll("[^0-9.]", ""));
			selectedProductNamesString.add(priceParing);
		}


		return selectedProductNamesString;
	}

	public int getAddedProductCount() {

		return driver.findElements(nrOfProducts).size();
	}

	public void sortProductsBy(String option) {


		Select select = new Select(driver.findElement(sortingDropdown));

		select.selectByVisibleText(option);

	}

	public boolean isProductFilterIconDisplayed() {

		return ElementWaits.waitForVisibility(driver,sortingDropdown).isDisplayed();
	}

	public int getCartBadgeIconCount() {

		return Integer.parseInt(ElementWaits.waitForVisibility(driver,shoppingCartBadge).getText());
	}

	public void clickCartBadgeIcon() {

		ElementWaits.waitForVisibility(driver, shoppingCartIcon).click();
	}

	public void clickMenuIcon() {

		ElementWaits.waitForVisibility(driver,menuButton).click();
	}

	public String getAddedProductPrice() {

		return  ElementWaits.waitForVisibility(driver,productPrice).getText();
	}

	public String getAddedProductName() {

		return  ElementWaits.waitForVisibility(driver,productName).getText();
	}

	public void clickLogout() {

		ElementWaits.waitForVisibility(driver,logoutButton).click();
	}

	// Page Behavior
	public boolean isInventoryPageLoaded() {

		boolean isLoaded = isMenuIconDisplayed() && isProductFilterIconDisplayed() && isShoppingCartIconDisplayed();

		return isLoaded;
	}

	public void addProductToCartAndOpenIt(String productName) {


		addProductByName(productName);
		clickCartBadgeIcon();

	}
	
	public void addProductToCart(String productName) {

		addProductByName(productName);

	}
}
