@login-user-fixture
Feature: as a user I would like to login to the site

  Scenario Outline: A series of failures login page

    Given the user is on the login page
    When the user enters <emailValue> into the username field
    And the user enters <passwordValue> into the password field
    And the user submits the login form
    Then the user is redirected back to the login page
    And the username field has the error <emailFieldError>
    And the password field has the error <passwordFieldError>

  Examples: examples of failure logins with error messages

    | emailValue          | passwordValue      | emailFieldError                                 | passwordFieldError                                       |
    | mkelly              | password           | username is not found - please register         | nothing                                                  |
    | mkelly@gmail.com    | password           | User is not active                              | nothing                                                  |
    | mkelly28@gmail.com  | 123456             | nothing                                         | password you have entered is incorrect, please try again |

  Scenario: When the user is not logged in and the user tries to go to dashboard the user gets redirected

    Given the user is not logged in
    When the user tries to access the dashboard page
    Then the user is redirected back to the login page

  Scenario: On success show all the tasks of the user

    Given the user is on the login page
    When the user enters mkelly28@gmail.com into the username field
    And the user enters markkelly into the password field
    And the user submits the login form
    Then the user is redirected to the dashboard page