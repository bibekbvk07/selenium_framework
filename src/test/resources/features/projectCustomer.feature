Feature: Project customer scenario for OrangeHRM site

  Scenario: User must login to conduct these test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "Admin"
    And User enters password "admin123"
    And User clicks on login button
    And User is on the dashboard page
    And User clicks on time link

  Rule: Common rule for these test cases
    Background:
      Given User hovers and clicks on projectInfo

    @projectCustomer_navigation
    Scenario: Positive navigation to the Customers page
      When User clicks on my customers
      Then Customers page appears

    @projectCustomer_add
    Scenario: User can successfully add customers
      When User clicks on add button
      And User enters name "Taylor Corp1"
      And User type description "Join a great team with career growth opportunities"
      And User clicks on save button
      Then User should be able to add customers

    @projectCustomer_duplicate_name
    Scenario: User can't add duplicate names
      When User clicks on add button
      And User enters name "Taylor Corp"
      Then User should see error message

#    @projectCustomer_delete
#    Scenario: User can successfully delete the added customers
#      When User selects the checkbox name "Dominium"
#      And User clicks delete button
#      And User gets alert pop up text
#      And User dismiss the alert pop up
#      Then Alert pop should be dismissed


