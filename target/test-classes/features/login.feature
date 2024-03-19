Feature: Login scenario for OrangeHRM site

  @launch_url
  Scenario: Positive url launch
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    Then Login page appears

  @invalid_login1
  Scenario: User receives invalid login message
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "hello"
    And User enters password "admin123"
    And User clicks on login button
    Then User gets an invalid login message
    
 @invalid_login2
 Scenario: User receives invalid login message
   Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
   And User enters username "Admin"
   And User enters password "hello123"
   And User clicks on login button
   Then User gets an invalid login message

  @invalid_login3
  Scenario: User receives invalid login message
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "hello"
    And User enters password "hello123"
    And User clicks on login button
    Then User gets an invalid login message

  @login@smoke
  Scenario: Positive credential login
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "Admin"
    And User enters password "admin123"
    And User clicks on login button
    Then User is redirected to the dashboard page

#    Is this approach a best practice? Having a test dependency? Isn't it best to have a independent and self contained scenarios

#    @login@logout
#    Scenario: Positive credential logout
#      When User clicks on user drop down menu
#      And User clicks on logout
#      Then Login page appears

  @logout
  Scenario: Positive credential logout
    Given User is on the dashboard page
    When User clicks on user drop down menu
    And User clicks on logout
    Then Login page appears

