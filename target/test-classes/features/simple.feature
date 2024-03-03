Feature: All test cases for positive & negative scenarios

  @simplest
  Scenario: Validate positive login flow
    Given User initialize the browser
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    Then User should be able to login

  @smoke@Regression@Userstory
  Scenario Outline: Demo scenario outline
    Given HR searching for "<Job Title>" for post of "Two" "<Vacancy>"

    Examples:
      |Job Title| Vacancy|
      |QA Lead|SDET|
      |UI Lead|Architect|

  @dataexample
  Scenario: Login with positive/correct credentials
    Given User is in "Suacedemo" homepage "https://www.saucedemo.com/"
    When User enters correct credentials
      |standard_user|secret_sauce|
    Then User should be able to login