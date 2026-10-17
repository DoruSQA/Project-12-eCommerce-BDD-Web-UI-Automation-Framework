
Feature: Product Sorting

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user is on the inventory page

  @Regression @ProductCatalog @TC_PS_001
  Scenario: Sort products by name in ascending order
    When the user sorts products by "Name (A to Z)"
    Then the products should be sorted by name in ascending order

  @Regression @ProductCatalog @TC_PS_002
  Scenario: Sort products by name in descending order
    When the user sorts products by "Name (Z to A)"
    Then the products should be sorted by name in descending order

  @Regression @ProductCatalog @TC_PS_003
  Scenario: Sort products by price in ascending order
    When the user sorts products by "Price (low to high)"
    Then the products should be sorted by price in ascending order

  @Regression @ProductCatalog @TC_PS_004
  Scenario: Sort products by price in descending order
    When the user sorts products by "Price (high to low)"
    Then the products should be sorted by price in descending order
