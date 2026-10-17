
Feature: Shopping Cart

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the inventory page

  @Smoke @Regression @ShoppingCart @TC_CART_001
  Scenario: Add one product to the cart
    When the user adds the "Sauce Labs Backpack" to the cart
    Then the cart badge should display the number of selected products
    When the user opens the shopping cart
    Then the cart page heading should be displayed
    And the cart page URL should be correct
    And the cart should contain the selected product
    And the cart should display the correct product price

  @Regression @ShoppingCart @TC_CART_002
  Scenario: Add multiple products to the cart
    When the user adds the following products to the cart:
      | Sauce Labs Backpack |
      | Sauce Labs Bolt T-Shirt |
    Then the cart badge should display the number of selected products
    When the user opens the shopping cart
    Then the cart page heading should be displayed
    And the cart page URL should be correct
    And the cart should contain all selected products
    And the cart should display the correct prices for all selected products

  @Regression @ShoppingCart @TC_CART_003
  Scenario: Remove an existing product from the cart
    When the user adds the "Sauce Labs Backpack" to the cart
    And the user opens the shopping cart
    When the user removes the "Sauce Labs Backpack" from the cart
    Then the cart badge count should decrease by one
    And the number of products in the cart should decrease by one
