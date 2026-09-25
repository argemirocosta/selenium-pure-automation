Feature: Sortable data tables
  As a user of The Internet
  I want to read and sort the data table
  So that I can find the information I need

  Background:
    Given I am on the tables page

  Scenario: Read a row by last name
    Then the row for "Doe" should contain:
      | First Name | Jason            |
      | Email      | jdoe@hotmail.com |
      | Due        | $100.00          |

  Scenario: Sort by last name
    When I sort the table by "Last Name"
    Then the "Last Name" column should be sorted alphabetically

  Scenario: Sort by due amount
    When I sort the table by "Due"
    Then the "Due" column should be sorted by amount
