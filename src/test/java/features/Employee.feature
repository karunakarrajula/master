Feature: Validating Employee Rest API's

Scenario: Create new employee
	Given Create new employee 
	When user calls "CreateNewEmployeeAPI" with "POST" http requests
	Then API call get success with status codes 200
	And "status" in responsed body is displayed as "success"
    And verify Id created maps to new employee using "GetEmployeeAPI"

Scenario: Update employee 
	Given Update employee details 
	When user calls "UpdateEmployeeAPI" with "PUT" http requests
	And "status" in responsed body is displayed as "success"
    And verify Id created maps to new employee using "GetEmployeeAPI"

Scenario: Delete employee record
	Given Delete employee record from DB 
	When user calls "DeleteEmployeeAPI" with "DELETE" http requests
	Then API call get success with status codes 200
	And "status" in responsed body is displayed as "success"   