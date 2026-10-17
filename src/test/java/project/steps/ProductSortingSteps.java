package project.steps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.base.DriverFactory;
import project.pages.InventoryPage;

public class ProductSortingSteps {

    private InventoryPage inventoryPage;


    public ProductSortingSteps() {
        WebDriver driver = DriverFactory.getDriver();
        inventoryPage = new InventoryPage(driver);
    }

    @When("the user sorts products by {string}")
    public void theUserSortsProductsBy(String sortingOption) {
        inventoryPage.sortProductsBy(sortingOption);
    }

    // Scenario 1: Sort products by name in ascending order

    @Then("the products should be sorted by name in ascending order")
    public void theProductsShouldBeSortedByNameInAscendingOrder() {

        List<String> actualProducts = inventoryPage.getAllProductNames();
        List<String> expectedProducts = new ArrayList<>(actualProducts);

        Collections.sort(expectedProducts);

        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    // Scenario 2: Sort products by name in descending order

    @Then("the products should be sorted by name in descending order")
    public void theProductsShouldBeSortedByNameInDescendingOrder() {

        List<String> actualProducts = inventoryPage.getAllProductNames();
        List<String> expectedProducts = new ArrayList<>(actualProducts);

        Collections.sort(expectedProducts, Collections.reverseOrder());

        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    // Scenario 3: Sort products by price in ascending order

    @Then("the products should be sorted by price in ascending order")
    public void theProductsShouldBeSortedByPriceInAscendingOrder() {

        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assertions.assertEquals(expectedPrices, actualPrices);
    }

    // Scenario 4: Sort products by price in descending order

    @Then("the products should be sorted by price in descending order")
    public void theProductsShouldBeSortedByPriceInDescendingOrder() {

        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices, Collections.reverseOrder());

        Assertions.assertEquals(expectedPrices, actualPrices);
    }
}
