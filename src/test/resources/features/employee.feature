Feature: Employee Management
  As a user, I want to manage employees

  Scenario: Create an Employee
    Given an employee with name "Vydhushya", age 20, and salary 25000
    When I send a POST request to "/employee/create"
    Then the response status should be 201
    And the response body should contain "Employee Created Successfully"


  Scenario: Get Employee By Id
    Given an employee with ID 2 exists
    When I send a GET request to "/employee/view/2"
    Then the response status should be 200
    And the response should contain "Vydhushya"



  Scenario: Get All Employees
    Given there are employees in the database
    When I send a GET request for all employees "/employee/view/all"
    Then the response status should be 200
    And the response should return list of employees


