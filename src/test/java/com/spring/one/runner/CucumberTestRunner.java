package com.spring.one.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.spring.one.stepdefinitions", "com.spring.one.config"},
        plugin = {"pretty", "json:target/cucumber-report.json"},
        monochrome = true
)

public class CucumberTestRunner {

}
