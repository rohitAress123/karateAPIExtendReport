@login1
Feature: user details

  Background: 
    * url 'https://reqres.in/api/users/2'
    * def expectedoutput = read('../../PayLoadData/assertionResult.json')

  Scenario: request list of user detail
    # Given path 2
    When method GET
    Then status 200
    And print response
    Then match response == expectedoutput
    And match response.data.id == 2
    And match response.data.first_name == "Janet"

  Scenario: request list of user detail
    # Given path 2
    And header Accept = 'application/xml'
    When method GET
    Then status 200
    And print response
    Then match response == expectedoutput
    And match response.data.id == 2
    And match response.data.first_name == "Janet"
