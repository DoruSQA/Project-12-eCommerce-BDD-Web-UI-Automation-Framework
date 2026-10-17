package project.steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import project.base.DriverFactory;
import project.expected.InventoryExpected;
import project.expected.LoginExpected;
import project.pages.InventoryPage;
import project.pages.LoginPage;

public class LoginSteps {

	private WebDriver driver;
	public LoginPage loginPage;
	public InventoryPage inventoryPage;

	@Before(order = 1)
	public LoginSteps() {
		this.driver = DriverFactory.getDriver();
		this.loginPage = new LoginPage(driver);
		this.inventoryPage = new InventoryPage(driver);
	}

	// Background
	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage() {
		loginPage.navigateTo(LoginExpected.URL);
	}

	// Scenario 1: Login successfully with valid credential
	@When("the user logs in with username {string} and password {string}")
	public void theUserLogsInWithValidCredentials(String username, String password) {
		loginPage.authentication(username, password);
	}

	@Then("the user should be redirected to the inventory page")
	public void theUserShouldBeRedirectedToTheInventoryPage() {
		Assertions.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
	}

	@And("the menu icon should be visible")
	public void theMenuIconShouldBeVisible() {
		Assertions.assertTrue(inventoryPage.isMenuIconDisplayed());
	}

	@And("the shopping cart icon should be visible")
	public void theShoppingCartIconShouldBeVisible() {
		Assertions.assertTrue(inventoryPage.isShoppingCartIconDisplayed());
	}

	// Scenario 2: Login successfully using the Enter key

	@When("the user enters username {string} and password {string}")
	public void theUserEntersCredentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@And("the user submits the login form using the Enter key")
	public void theUserSubmitsTheLoginFormUsingEnter() {
		loginPage.pressEnter();
	}

	@And("the inventory page heading should be displayed")
	public void theInventoryPageHeadingShouldBeDisplayed() {
		Assertions.assertEquals(InventoryExpected.HEADING, inventoryPage.getPageHeading());
	}

	// Scenarios 3–7

	@Then("the login error message should indicate invalid credentials")
	public void theLoginErrorMessageShouldIndicateInvalidCredentials() {
		Assertions.assertEquals(LoginExpected.FAILED_MSG_WITH_INVALID_CREDENTIALS, loginPage.getErrorMessage());
	}

	@Then("the login error message should indicate that the username is required")
	public void theLoginErrorMessageShouldIndicateUsernameRequired() {
		Assertions.assertEquals(LoginExpected.FAILED_MSG_WITH_EMPTY_USERNAME, loginPage.getErrorMessage());
	}

	@Then("the login error message should indicate that the password is required")
	public void theLoginErrorMessageShouldIndicatePasswordRequired() {
		Assertions.assertEquals(LoginExpected.FAILED_MSG_WITH_EMPTY_PASSWORD, loginPage.getErrorMessage());
	}

	@Then("the login error message should indicate that the user is locked out")
	public void theLoginErrorMessageShouldIndicateUserLockedOut() {
		Assertions.assertEquals(LoginExpected.FAILED_MSG_LOCKEDOUT_USER, loginPage.getErrorMessage());
	}

	@And("the user should remain on the login page")
	public void theUserShouldRemainOnTheLoginPage() {
		Assertions.assertEquals(LoginExpected.URL, loginPage.getUrl());
	}

	// Scenario 8: Logout successfully and prevent access to the inventory page

	@Given("the user is on the inventory page")
	public void theUserIsOnTheInventoryPage() {
		Assertions.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
	}

	@When("the user opens the navigation menu")
	public void theUserOpensTheNavigationMenu() {
		inventoryPage.clickMenuIcon();
	}

	@And("the user selects logout")
	public void theUserSelectsLogout() {
		inventoryPage.clickLogout();
	}

	@Then("the user should be redirected to the login page")
	public void theUserShouldBeRedirectedToTheLoginPage() {
		Assertions.assertTrue(loginPage.isLoginButtonVisible());
		Assertions.assertEquals(LoginExpected.URL, loginPage.getUrl());
	}

	@And("the login button should be visible")
	public void theLoginButtonShouldBeVisible() {
		Assertions.assertTrue(loginPage.isLoginButtonVisible());
	}

	@When("the user navigates directly to the inventory page")
	public void theUserNavigatesDirectlyToTheInventoryPage() {
		loginPage.navigateTo(InventoryExpected.URL);
	}

}
