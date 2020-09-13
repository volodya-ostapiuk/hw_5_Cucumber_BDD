package com.epam.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/com.epam/features/gmail.feature",
        glue = {"com/epam/steps"})
public class RunnerTest {
    private TestNGCucumberRunner cucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        cucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper featureWrapper) {
        cucumberRunner.runCucumber(featureWrapper.getCucumberFeature());
    }

    @DataProvider(parallel = true)
    public Object[][] features() {
        return cucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        cucumberRunner.finish();
    }
}
