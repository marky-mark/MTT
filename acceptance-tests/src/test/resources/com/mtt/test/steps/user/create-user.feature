@create-user-fixture
Feature: As a user i would like to register with the Site

  Scenario: I am a user that does not enter any data into the First name field
    Given the user is on the registration page
    And a user enters nothing into the first name field
#    When the user makes an API call to create a user
#    Then authentication should fail for first name field

#  Scenario: I am a user that does not enter any data into the Last name field
#    Given a user enters nothing into the last name field
#    When the user makes an API call to create a user
#    Then authentication should fail for last name field
#
#  Scenario: I am a user that does not enter any data into the password field
#    Given a user enters nothing into the password field
#    When the user makes an API call to create a user
#    Then authentication should fail for password field
#
#  Scenario: I am a user that does not enter any data into the confirmed password field
#    Given a user enters nothing into the confirmed password field
#    When the user makes an API call to create a user
#    Then authentication should fail for confirmed password field
#
#  Scenario: I am a user that does not enter any data into the email address field
#    Given a user enters nothing into the email address field
#    When the user makes an API call to create a user
#    Then authentication should fail for email address field
#
#  Scenario: I am a user that does not enter any data into the confirmed email address field
#    Given a user enters nothing into the confirmed email address field
#    When the user makes an API call to create a user
#    Then authentication should fail for confirmed email address field
#
#  Scenario: I am a user does not enter any data into the phone number field
#    Given a user enters nothing into the telephone number field
#    When the user makes an API call to create a user
#    Then authentication should fail for telephone number field
#
#  Scenario: I am a user does not enter any data into the ip address field
#    Given a user enters nothing into the ip address field
#    When the user makes an API call to create a user
#    Then authentication should fail for ip address field
#
#  Scenario: I am a user that enters less than 6 characters for the password field
#    Given a user enters less than 6 characters for the password field
#    When the user makes an API call to create a user
#    Then authentication should fail for password field
#
#  Scenario: I am a user that enters a non matching confirmed password field
#    Given a user enters a non matching confirmed password field
#    When the user makes an API call to create a user
#    Then authentication should fail for confirmed password field
#
#  Scenario: I am a user that specifies valid data for registration
#    Given a user enters all valid data
#    When the user makes an API call to create a user
#    Then a user should be created with no validation failures
#    And an activation email should be sent to the user
#
#  Scenario: I am a user that tries to use an email address already registered
#    Given a user enters all valid data
#    When the user makes an API call to create a user
#    Then authentication should fail for email address field