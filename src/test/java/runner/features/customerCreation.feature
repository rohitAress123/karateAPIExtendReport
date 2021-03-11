Feature: create customer post call

  Background: 
    * url 'https://reqres.in/'
    * header Accept = 'application/json'
    * def payload = read('../../PayLoadData/InputPayLoad.json')
@smoke
  Scenario: create customer
    Given path '/api/users'
    And request payload[0]
    When method post
    Then status 201
    #Then print response
    #Then print 'username: '
