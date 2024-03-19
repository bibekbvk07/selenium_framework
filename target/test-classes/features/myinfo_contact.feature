Feature: My info contact details scenario for Orange HRM site

  Scenario: User must login to conduct these test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "Admin"
    And User enters password "admin123"
    And User clicks on login button
    And User is on the dashboard page
    And User clicks on my info link

  @contact_details_update
  Scenario: Positive update of contact details
    Given User clicks on contact details