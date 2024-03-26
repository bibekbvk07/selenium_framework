Feature: Logout scenario for OrangeHRM site
  Background:
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and validates title "OrangeHRM"
    When User enters "username" "Admin"
    And User enters "password" "admin123"
    And User clicks on login button
    Then User is redirected to the dashboard page and validates "Dashboard"

  @logout
  Scenario: Positive credential logout
    When User clicks on user drop down menu and validates header "Logout"
    And User clicks on logout and validates "Login" page
    Then Login page appears and validates header "Login"
