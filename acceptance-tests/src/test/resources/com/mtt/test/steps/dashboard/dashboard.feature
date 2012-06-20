@dashboard-fixture
Feature: as a user I would like to create delete and edit tasks

  Scenario: when a user is logged in the user should see all the adverts on the dashboard

    Given the user is logged in as mkelly with the password 123456
    When the user is on the dashboard page
    Then the user should see the following tasks:
      | Id  | Title                  | Description      | Checked |
      | 1   | title 1                | description 1    |   0     |
      | 2   | title 2                | description 2    |   0     |
      | 3   | title 3                | description 3    |   1     |
