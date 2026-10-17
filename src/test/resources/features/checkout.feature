Feature: Checkout Information

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the inventory page
    When the user adds the "Sauce Labs Bolt T-Shirt" to the cart
    And the user opens the shopping cart
    And the cart page heading should be displayed
    And the cart page URL should be correct
    When the user proceeds to checkout
    Then the checkout information page should be displayed

  @Regression @Checkout @TC_CHECKOUT_001
  Scenario: Checkout with an empty first name
    When the user submits checkout information with first name "" last name "Doe" and ZIP code "12345"
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the first name is required

  @Regression @Checkout @TC_CHECKOUT_002
  Scenario: Checkout with an empty last name
    When the user submits checkout information with first name "John" last name "" and ZIP code "12345"
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the last name is required

  @Regression @Checkout @TC_CHECKOUT_003
  Scenario: Checkout with an empty ZIP code
    When the user submits checkout information with first name "John" last name "Doe" and ZIP code ""
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the ZIP code is required

  @Regression @Checkout @TC_CHECKOUT_004
  Scenario: Checkout with whitespace-only information
    When the user submits checkout information with first name " " last name " " and ZIP code " "
    Then the checkout information page URL should remain unchanged
    And the checkout information page heading should be displayed

  @Regression @Checkout @TC_CHECKOUT_005
  Scenario: Checkout with a first name exceeding the maximum length
    When the user submits checkout information with first name "ABCDEFGHIJKLMNOPQRSTU" last name "Doe" and ZIP code "12345"
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the first name is too long

  @Regression @Checkout @TC_CHECKOUT_006
  Scenario: Checkout with a last name exceeding the maximum length
    When the user submits checkout information with first name "John" last name "ABCDEFGHIJKLMNOPQRSTU" and ZIP code "12345"
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the last name is too long

  @Regression @Checkout @TC_CHECKOUT_007
  Scenario: Checkout with a ZIP code exceeding the maximum length
    When the user submits checkout information with first name "John" last name "Doe" and ZIP code "12345678"
    Then the checkout information page URL should remain unchanged
    And the checkout error message should indicate that the ZIP code is too long
