@smoke
Feature: Assertion POST Method

  Background: 
    * def jsonPayLoad = read('../../PayLoadData/createUser.json')
    * url 'https://reqres.in/api/users'
    * headers {content-type : 'application/json', Accept : 'application/json'}
    * def expectedOutput = read('../../PayLoadData/expectedOutputForPOST.json')

  Scenario: create a user
    And request jsonPayLoad
    When method POST
    Then status 201
    Then print 'response....>>>>>>>>>>>'+ response
    And match response == expectedOutput
    Then match response.name == "morpheus"
    Then match response contains {"job": "leader"}
