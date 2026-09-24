Feature: Login
  As a user of The Internet
  I want to log into the secure area
  So that I can access protected content

  @smoke
  Scenario: Successful login
    Given I am on the login page
    When I log in with valid credentials
    Then I should be on the secure area
    And I should see a success message "You logged into a secure area!"

  Scenario: Logout
    Given I am logged in
    When I log out
    Then I should be on the login page
    And I should see a success message "You logged out of the secure area!"

  Scenario Outline: Login with invalid <field>
    Given I am on the login page
    When I log in with username "<username>" and password "<password>"
    Then I should be on the login page
    And I should see an error message "<message>"

    Examples:
      | field    | username | password             | message                   |
      | username | invalid  | SuperSecretPassword! | Your username is invalid! |
      | password | tomsmith | invalid              | Your password is invalid! |
