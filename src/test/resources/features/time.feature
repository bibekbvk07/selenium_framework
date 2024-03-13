Feature: Time scenario for OrangeHRM site

  Scenario: User must login to conduct these test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "Admin"
    And User enters password "admin123"
    And User clicks on login button
    And User is on the dashboard page
    And User clicks on time link

  Rule: Common rule for these 4 test cases
    Background:
      Given User hovers and clicks on attendance

    @time_attendance_myRecords
    Scenario: Positive navigation to the My Record page
      When User clicks on my records
      Then My record page appears

    @time_attendance_punchInOut
    Scenario: Positive navigation to the punch in/out page
      When User clicks on punch in-out
      Then Punch in-out page appears

    @time_attendance_employeeRecords
    Scenario: Positive navigation to the Employee Records page
      When User clicks on employee records
      Then Employee records page appears

    @time_attendance_employeeRecords_list
    Scenario: Positive navigation to the Employee Records page
      Given Employee records page appears
      Then Employee should be able to see the list

    @time_attendance_configuration
    Scenario: Positive navigation to the Attendance configuration page
      When User clicks on configuration
      Then Configuration page appears

    @time_attendance_configuration_uncheck
    Scenario: User uncheck all the checkbox options
      Given Configuration page appears
      When User unchecks all the option
      And User clicks on save
      Then User should be able to see the changes





