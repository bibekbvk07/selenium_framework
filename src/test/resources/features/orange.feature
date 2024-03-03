
Feature: Login scenario for OrangeHRM

  @LoginHRM
    Scenario: Positive credential login
      Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
      When Users enters username "Admin"
      And Users enters password "admin123"
      And User clicks on Login
      Then User should be able navigate Homepage

  @Invalid_Login_HRM1
    Scenario: Negative credential login
      Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
      When Users enters username "hello"
      And  Users enters password "hello123"
      And User clicks on Login
      Then User should be able to see invalid login message

  Rule: Common rule for the test cases below
      Background:
        Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        When Users enters username "Admin"
        And Users enters password "admin123"
        And User clicks on Login
        Then User should be able navigate Homepage

            @MyInfoTest
            Scenario: Positive click on My Info link
              When User clicks on My Info link
              Then User should see Personal Detail Page

            @OrangeHRM_Homepage_Scroll
            Scenario: Positive scroll on the Homepage
              When User scrolls down the Homepage
              Then the content on the Homepage should be visible after scrolling

            @OrangeHRM_MyInfo_NewTab
            Scenario: User opens 'My Info' in a new tab and switches to the new tab.
              When User navigates to My Info section and opens link in new tab
              And User switches the window to new tab
              Then User should be in Personal Details tab

            @OrangeHRMTest3
            Scenario: User updates personal details
              When User navigates to My Info section and opens link in new tab
              And User switches the window to new tab
              Then User should be in Personal Details tab
              When User clicks on firstName input field
              And User clears the firstNameinput field
              And User updates the firstName input field
              When User clicks on lastName input field
              And User clears the lastName input field
              And User updates the lastName input field
              And User clicks on Save and
              And User closes the current tab
              Then User should be in the main tab

