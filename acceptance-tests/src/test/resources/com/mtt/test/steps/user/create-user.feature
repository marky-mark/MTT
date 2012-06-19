@create-user-fixture
Feature: As a user i would like to register with the Site

  Scenario Outline: I am a user that enters in invalid data into a field on the registration form

    Given the user is on the registration page
    And a user enters <input> into the <field> field
    When the user submits the form
    Then the user is returned to the registration page
    And the <field> field error will be "<error>"

  Examples: Validation examples

    | field                       | input                  | error                            |
    | firstName                   | nothing                | must have a first name           |
    | firstName                   | <html>                 | no html is allowed               |
    | firstName                   | 12345678910            | must have certain length         |
    | lastName                    | nothing                | must have a last name            |
    | lastName                    | <html>                 | no html is allowed               |
    | lastName                    | 12345678910            | must have a certain length       |
    | emailAddress                | nothing                | email is missing                 |
    | emailAddress                | <html>                 | not a valid email                |
    | emailAddress                | 12345678910            | not a valid email                |
    | emailAddress                | mkelly@gmail.com       | email already exists             |
    | confirmedEmailAddress       | mkelly28@gmail.com     | email addresses foes not match   |
    | password                    | nothing                | password is missing              |
    | password                    | 12345678910            | password max length              |
    | password                    | password               | nothing                          |
    | confirmedPassword           | nothing                | please confirm your password     |
    | confirmedPassword           | password2              | passwords do not match           |

  Scenario: A User Enters in Valid data which brings the user to the success page

    Given the user is on the registration page
    And a user enters valid data into the fields
    When the user submits the form
    Then the user is brought to the success page