Feature: Time scenario for OrangeHRM site

  Scenario: User must login to conduct the test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and validates title "OrangeHRM"
    And User enters "username" "Admin"
    And User enters "password" "admin123"
    And User clicks on login button
    And User is redirected to the dashboard page and validates "Dashboard"
    And User clicks "time" in the side panel navigation bar and validates header "Time"

  Rule: Common rule for these 4 test cases
    Background:
      Given User hovers and clicks on "attendance" and validates header "My Records"

    @time_attendance_myRecords
    Scenario: Positive navigation to the My Record page
      When User clicks on "my records" dropdown menu and validates header "My Attendance Records"

    @time_attendance_punchInOut
    Scenario: Positive navigation to the punch in/out page
      When User clicks on "punch in_out" dropdown menu and validates header "Punch Out"


    @time_attendance_employeeRecords
    Scenario: Positive navigation to the Employee Records page
      When User clicks on "employee records" dropdown menu and validates header "Employee Attendance Records"
      Then Employee should be able to see the list


    @time_attendance_configuration
    Scenario: Positive navigation to the Attendance configuration page
      When User clicks on "configuration" dropdown menu and validates header "Attendance Configuration"
      And User unchecks all the option
      And User clicks on save
      Then User should be able to see the changes







