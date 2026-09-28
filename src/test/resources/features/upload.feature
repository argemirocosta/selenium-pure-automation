Feature: File upload
  As a user of The Internet
  I want to upload a file
  So that I can share it with the application

  Scenario: Upload a file
    Given I am on the upload page
    When I upload the file "sample.txt"
    Then I should see "sample.txt" as uploaded
