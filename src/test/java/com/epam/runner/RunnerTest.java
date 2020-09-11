package com.epam.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/epam/gmail.feature",
        glue = {"com.epam.step.StepDefinition"})
public class RunnerTest {
}
