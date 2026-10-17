
Feature: End-to-End Purchase

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the inventory page

  @Smoke @Regression @Purchase @TC_CHECKOUT_008
  Scenario: Purchase one item with valid customer details
    When the user adds the "Sauce Labs Backpack" to the cart
    And the user opens the shopping cart
    And the user records the cart product details
    And the user proceeds to checkout
    And the user submits checkout information with first name "John" last name "Doe" and ZIP code "12345"
    Then the checkout overview should contain the expected product details
    And the checkout overview should display the correct total price
    When the user completes the purchase
    Then the purchase confirmation icon should be visible
    And the purchase confirmation title should be displayed
    And the purchase confirmation message should be displayed

  @Regression @Purchase @TC_CHECKOUT_009
  Scenario: Purchase multiple items with valid customer details
    When the user adds the following products to the cart:
      | Sauce Labs Backpack       |
      | Sauce Labs Bolt T-Shirt   |
    And the user opens the shopping cart
    And the user records the cart product details
    And the user proceeds to checkout
    And the user submits checkout information with first name "John" last name "Doe" and ZIP code "12345"
    Then the checkout overview should contain the expected product details
    And the checkout overview should display the correct total price
    When the user completes the purchase
    Then the purchase confirmation icon should be visible
    And the purchase confirmation title should be displayed
    And the purchase confirmation message should be displayed
