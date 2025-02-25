package com.spring.one.stepdefinitions;

import com.spring.one.vo.EmployeeVO;
import java.util.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.equalTo;

public class EmployeeStepDefinitions {

    private EmployeeVO employee;
    private List<EmployeeVO> employees;
    private Response response;

    @Given("an employee with name {string}, age {int}, and salary {int}")
    public void createEmployeeObject(String name, int age, int salary) {
        employee = new EmployeeVO(1L, name, age, salary);
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        String url = "http://localhost:8082"+endpoint;
        response = RestAssured.given()
                .contentType("application/json")
                .body(employee)
                .post(url);
    }

    @Then("the response status should be {int}")
    public void verifyResponseStatus(int status) {
        response.then().statusCode(status);
    }

    @Then("the response body should contain {string}")
    public void verifyResponseBody(String expectedMessage) {
        response.then().body(equalTo(expectedMessage));
    }

    @Given("an employee with ID {int} exists")
    public void getEmployeeWithId(long id){
        RestAssured.given()
                .contentType("application/json")
                .body(new EmployeeVO(id, "Vydhushya", 20, 25000))
                .post("http://localhost:8082/employee/create");
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint){
        response = RestAssured.get("http://localhost:8082"+endpoint);
    }

    @Then("the response should contain {string}")
    public void verifyEmployeeName(String expectedName) {
        response.then().body("name", equalTo(expectedName));
    }


//    @And("the response should return list of employees")
//    public void ListOfEmployees() {
//        response.then().
//    }

    @Given("there are employees in the database")
    public void CreateListOfEmployees() {
        employee = new EmployeeVO(1L, "name", 10, 2000);
        RestAssured.given()
                .contentType("application/JSON")
                .body(employee)
                .post("http://localhost:8082/employee/create");
    }

    @When("I send a GET request for all employees {string}")
    public void GETAllEmployees(String endpoint) {
        response = RestAssured.get("http://localhost:8082"+endpoint);
    }

    @And("the response should return list of employees")
    public void ListOfEmployees() {
        Assertions.assertEquals(200,response.getStatusCode());
    }
}
