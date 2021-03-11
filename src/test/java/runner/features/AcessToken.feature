@smoke
Feature: call to token genration feature

Background: 
* url 'https://reqres.in/api/register'

Scenario: Authentication Method test
* def params =
"""
{
	'email' : 'eve.holt@reqres.in',
	'password' : 'pistol'
}
"""
And form fields params
When method POST
Then status 200
Then print 'response....'+ response
