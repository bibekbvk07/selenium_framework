package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features/bookingapi.feature"}, glue = {"org/example/stepdefinitions"}, plugin = {"pretty", "html:target/cucumber-report.html"})
public class CucumberRunner extends AbstractTestNGCucumberTests {


}