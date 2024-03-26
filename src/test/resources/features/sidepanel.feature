Feature: Test case for side panel scenario for OrangeHRM site

  Scenario: User must login to perform test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and validates title "OrangeHRM"
    And User enters "username" "Admin"
    And User enters "password" "admin123"
    And User clicks on login button
    Then User is redirected to the dashboard page and validates "Dashboard"

  @positive_click_on_every_link
  Scenario: User positively clicks on every link on side panel and validates the header
    When User clicks "admin" in the side panel navigation bar and validates header "Admin"
    And User clicks "pim" in the side panel navigation bar and validates header "PIM"
    And User clicks "leave" in the side panel navigation bar and validates header "Leave"
    And User clicks "time" in the side panel navigation bar and validates header "Time"
    And User clicks "recruitment" in the side panel navigation bar and validates header "Recruitment"
    And User clicks "my info" in the side panel navigation bar and validates header "PIM"
    And User clicks "performance" in the side panel navigation bar and validates header "Performance"
    And User clicks "dashboard" in the side panel navigation bar and validates header "Dashboard"
    And User clicks "directory" in the side panel navigation bar and validates header "Directory"
    And User clicks "claim" in the side panel navigation bar and validates header "Claim"
    Then User clicks "buzz" in the side panel navigation bar and validates header "Buzz"

#  @maintenance_link_new_window
#  Scenario: User positively opens maintenance link to new window
#    When User opens "maintenance" link on new tab and validates the url "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee"

  @maintenance_administrator_access
  Scenario: User provides positive administrator access
    When User opens "maintenance" link on new tab and validates the url "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee"
    And User enters "password" "admin123"
    Then User clicks on "confirm button" and validates header "Maintenance"

