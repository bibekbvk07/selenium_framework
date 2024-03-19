Feature: My info personal details scenario for Orange HRM site

  Scenario: User must login to conduct these test cases
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And User enters username "Admin"
    And User enters password "admin123"
    And User clicks on login button
    And User is on the dashboard page
    And User clicks on my info link

  @profilePic_change
  Scenario: Positive change of profile picture
      Given User clicks on profile picture
      When User uploads the profile picture
      And  User clicks on save button
      Then User profile picture should be changed

  @personalDetails_update
  Scenario: Positive change of personal details
    Given User clicks on personal details
    When User enters firstname "Jenny"
    And User enters middle name ""
    And User enters lastname "Carlson"
    And User enters employee id "12345"
    And User enters other id "1001"
    And User enters driver's license number "A123-342-879-910"
    And User enters license expiry date "2023-11-03"
    And User selects nationality "british"
    And User selects marital status "married"
    And User enters date of birth "1995-11-05"
    And User selects the gender "f"
    Then User clicks on save button

  @custom_fields
  Scenario: Positive update on custom fields
    Given User scrolls down on the custom fields
    And User selects the blood type "O+"
    And User enters test_field "N/A"
    Then User clicks on custom_field save button

  @add_attachment
  Scenario: User can add attachments
    Given User scrolls down on the attachment section
    And User clicks on the add attachment button
    And User uploads the attachment
    And User types a comment "Hello World!"
    And User clicks on save button for the attachments
    Then User should be able to see the attachment in the records field

  @edit_record
  Scenario: User can edit the record
    Given User scrolls down on the attachment section
    When User clicks on the edit button of the record to be edited
    And  User uploads the attachment with new picture
    And User edits a comment "Hello Mario!"
    And User clicks on save button for the attachments
    Then User should be able to see the updated records

  @delete_record
  Scenario: User can delete the record
    Given User scrolls down on the attachment section
    When User clicks on the delete button of the record to be deleted
    And User clicks "delete" button on the alert pop up
    Then User selected record should be deleted

  @delete_all_record
  Scenario: User can delete all the records
    Given User selects all the records
    And User clicks on delete selected button
    And User clicks "delete" button on the alert pop up
    Then All records should be deleted