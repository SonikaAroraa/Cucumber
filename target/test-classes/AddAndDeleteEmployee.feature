Feature: Add and delete employee

Scenario: Add a new Employee using a POST request
  Given Add Employee payload from "CreateEmployee.json" file
  When user calls "createEmployee" with "POST" http request
  And verify created response to using "get" with "getEmployee"
  And Delete the created employee using "deleteEmployee"
