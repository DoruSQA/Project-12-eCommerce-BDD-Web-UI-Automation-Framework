Feature: Login

  Background:
    Given the user is on the login page

  @Smoke @Regression @Authentication @TC_LOGIN_001
  Scenario Outline: Login with valid credentials
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    And the menu icon should be visible
    And the shopping cart icon should be visible

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @Regression @Authentication @TC_LOGIN_002
  Scenario Outline: Login with valid credentials using Enter key
    When the user enters username "<username>" and password "<password>"
    And the user submits the login form using the Enter key
    Then the user should be redirected to the inventory page
    And the inventory page heading should be displayed
    And the menu icon should be visible
    And the shopping cart icon should be visible

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @Regression @Authentication @TC_LOGIN_003
  Scenario Outline: Login with valid username and wrong password
    When the user logs in with username "<username>" and password "<password>"
    Then the login error message should indicate invalid credentials
    And the user should remain on the login page

    Examples:
      | username      | password        |
      | standard_user | wrong_password  |

  @Regression @Authentication @TC_LOGIN_004
  Scenario Outline: Login with wrong username and valid password
    When the user logs in with username "<username>" and password "<password>"
    Then the login error message should indicate invalid credentials
    And the user should remain on the login page

    Examples:
      | username     | password     |
      | invalid_user | secret_sauce |

  @Regression @Authentication @TC_LOGIN_005
  Scenario Outline: Login with empty username and valid password
    When the user logs in with username "<username>" and password "<password>"
    Then the login error message should indicate that the username is required
    And the user should remain on the login page

    Examples:
      | username | password     |
      |          | secret_sauce |

  @Regression @Authentication @TC_LOGIN_006
  Scenario Outline: Login with valid username and empty password
    When the user logs in with username "<username>" and password "<password>"
    Then the login error message should indicate that the password is required
    And the user should remain on the login page

    Examples:
      | username      | password |
      | standard_user |          |

  @Regression @Authentication @TC_LOGIN_007
  Scenario Outline: Login with a locked-out user
    When the user logs in with username "<username>" and password "<password>"
    Then the login error message should indicate that the user is locked out
    And the user should remain on the login page

    Examples:
      | username        | password     |
      | locked_out_user | secret_sauce |

  @Smoke @Regression @Authentication @TC_LOGIN_008
  Scenario Outline: Logout successfully and prevent access to the inventory page
    Given the user logs in with username "<username>" and password "<password>"
    And the user is on the inventory page
    When the user opens the navigation menu
    And the user selects logout
    Then the user should be redirected to the login page
    And the login button should be visible
    When the user navigates directly to the inventory page
    Then the user should remain on the login page
    And the login button should be visible

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |