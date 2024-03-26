Feature: My info personal details section scenario for OrangeHRM site

  Scenario:
    Given User launches URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and validates title "OrangeHRM"
    And User enters "username" "Admin"
    And User enters "password" "admin123"
    And User clicks on login button
    And User is redirected to the dashboard page and validates "Dashboard"
    And User clicks "my info" in the side panel navigation bar and validates header "PIM"

  @profilePic_change
  Scenario: Positive change of profile picture
    Given User clicks on "profile picture" link and validates header "Change Profile Picture"
    When User uploads the attachment url "/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile1.jpg"
    And  User clicks on "save button" link and validates header "Change Profile Picture"
    Then User "profile picture" should be changed

  @personalDetails_update
  Scenario: Positive change of personal details
    Given User clicks on "personal details" link and validates header "Personal Details"
    When User enters "firstName" input "Kate"
    And User enters "middleName" input ""
    And User enters "lastName" input "Abdo"
    And User enters locator "employee id" input "413827"
    And User enters locator "other id" input "A1023"
    And User enters locator "Driver License Number" input "X123-342-179-019"
    And User enters locator "license expiry date" input "2025-11-03"
    And User enters dropdown locator "nationality" input "American"
    And User enters dropdown locator "marital status" input "Single"
    And User enters locator "date of birth" input "1990-15-04"
    And User selects the gender "f"
    Then User clicks on "personal details save button" link and validates locator "marital status" value "Single"

  @custom_fields
  Scenario: Positive update on custom fields
    Given User scrolls down to the "custom fields" section and validates header "Custom Fields"
    And User enters dropdown locator "blood type" input "AB-"
    And User enters locator "test field" input "My Blood Group"
    Then User clicks on "custom field save button" link and validates locator "blood type" value "AB-"

  @add_attachment
  Scenario: User can add attachments
    Given User scrolls down to the "attachment field" section and validates header "Attachments"
    And User clicks on "add attachment button" link and validates locator "add attachment" value "Add Attachment"
    And User uploads the attachment url "/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile.png"
    And User enters locator "textarea" input "Hello from the the universe."
    And User clicks on "add attachment save button" link and validates locator "attachment" value "Attachments"
    Then User should be able to see the attachment in the "records" field and validates with file name "profile.png"

  @edit_record
  Scenario: User can edit the record
    Given User scrolls down to the "attachment field" section and validates header "Attachments"
    When User clicks on the edit button of the record to be edited and validates header "Edit Attachment"
    And User uploads the attachment url "/Users/bibekamatya/ITSutra/Framework/selenium_framework/src/test/resources/images/profile1.jpg"
    And User enters locator "textarea" input "Hello Mario!"
    And User clicks on "edit attachment save button" link and validates locator "attachment" value "Attachments"
    Then User should see the updated records and validates description "Hello Mario!"

  @delete_record
  Scenario: User can delete the record
    Given User scrolls down to the "attachment field" section and validates header "Attachments"
    When User clicks on the delete button of the record to be deleted and validates header "Are you Sure?"
    And User clicks on "delete button" link and validates locator "attachment" value "Attachments"
    Then User selected record should be deleted

  @delete_all_record
  Scenario: User can delete all the records
    Given User selects all the records
    And User clicks on "delete selected" button and validates header "Are you Sure?"
    Then User clicks on "delete button" link if records available and validates locator "attachment" value "Attachments"
    Then All records should be deleted and count should be 0