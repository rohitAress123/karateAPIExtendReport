@login2
Feature: POST Method

Background: 
* url 'https://reqres.in/'

Scenario Outline: create a user
Given path 'api/users'
And request {"name":<name>,"job":<job>, "age":<age>}
When method POST
Then status 201
Then print 'response1....'+ response
* def result = response

Examples: 

	|read('../../PayLoadData/inputData.csv')|