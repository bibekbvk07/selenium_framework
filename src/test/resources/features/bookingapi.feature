#Feature: Booking API Authentication Scenario

#  @booking_getAll
#  Scenario: Retrieve all bookings
#    Given User initialize a request
#    When User request all booking and performs get operation
#    And API should respond with a 200 status code
#    Then User should receive list of booking IDs
#
#  @booking_byId
#  Scenario: Retrieve booking information by ID
#    Given there are existing bookings in the system
#    When User makes a GET request to the booking API with the given booking ID
#    And API should respond with a 200 status code
#    Then User booking details should be successfully extracted and printed

Feature: Booking Management

  @booking_getAll
  Scenario: Retrieve all bookings
    Given the base URL is "https://restful-booker.herokuapp.com"
    When a GET request is made to "/booking"
    Then the response status code should be 200
    And the response should contain a list of bookings
    And the booking ID is stored in the global variable

  @booking_byId
  Scenario: Retrieve booking by ID
    Given the base URL is "https://restful-booker.herokuapp.com"
    And the booking ID is stored in the global variable
    When a GET request is made to "/booking/{bookingid}" with stored booking Id
    Then the response status code should be 200
    And the response should contain the details of the booking with the stored ID