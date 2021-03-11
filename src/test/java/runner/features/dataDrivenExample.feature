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



Given path 'api/users/'+result.id
And request {"name":<name>,"job":<job>, "age":<age>}
And method POST
Then status 201
Then print 'response2....'+ response

Examples: 
|name    |job   |age|
|morpheus|leader|23 |