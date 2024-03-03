Feature: Test cases for the Time functionality

  Rule: Common rule for the test cases below
    Background:
      Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
      When Users enters username "Admin"
      And Users enters password "admin123"
      And User clicks on Login
      Then User should be able navigate Homepage

        @HRM_Time_NavigateTo_PunchIn
        Scenario: Positive navigation to Punch In page
          Given User clicks on Time link
          And User navigates to attendance tab and clicks on attendance dropdown
          And User clicks on PunchIn-Out
          Then User should be on Punch In page

          @HRM_Time_NavTabItems_Click
          Scenario: Positive clicks on all the navbar tab items
            Given User clicks on Time link
            And User clicks on all the navbar tab items
            Then User should be able to see nav tab items clicked

          @HRM_Time_PunchIn_Test
          Scenario: Positive punch in test
            Given User clicks on Time link
            And User navigates to attendance tab and clicks on attendance dropdown
            And User clicks on PunchIn-Out
            And User checks the status of punch-in and if punched in, user will punch out before punching in
            Then User should be able to punch in