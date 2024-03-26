Feature: Login scenario for OrangeHRM site

  Rule: Common given scenario for the test cases below
    Background:
      Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and validates title "OrangeHRM"

    @launch_url
    Scenario: Positive url launch
      Then Login page appears and validates header "Login"

    @invalid_login1
    Scenario: User receives invalid login message
      When User enters "username" "hello"
      And User enters "password" "admin123"
      And User clicks on login button
      Then User gets an invalid message "Invalid credentials"

    @invalid_login2
    Scenario: User receives invalid login message
      When User enters "username" "Admin"
      And User enters "password" "hello123"
      And User clicks on login button
      Then User gets an invalid message "Invalid credentials"

    @invalid_login3
    Scenario: User receives invalid login message
      When User enters "username" "hello"
      And User enters "password" "hello123"
      And User clicks on login button
      Then User gets an invalid message "Invalid credentials"

    @login
    Scenario: Positive credential login
      When User enters "username" "Admin"
      And User enters "password" "admin123"
      And User clicks on login button
      Then User is redirected to the dashboard page and validates "Dashboard"




