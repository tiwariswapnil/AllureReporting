package com.github.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.ITest;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.lang.reflect.Method;


@CucumberOptions(
        plugin =  {"html:target/cucumber-html-report",
                "pretty"
        },
        features = "src/test/resources/features/",
        glue = {"stepdefs"},
        dryRun = false,
        tags = "@Login")
public class TestNgRunner implements ITest  {

        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
                this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }

        @Test(groups = {"cucumber"},dataProvider = "scenarios")
        public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
                AllureLifecycle lifecycle = Allure.getLifecycle();
                lifecycle.updateTestCase(testResult -> testResult.setName(pickleWrapper.toString()));
                if (!pickleWrapper.getPickle().getTags().contains("@ignore")){
                        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
                }else{
                        throw new SkipException("Skipped Test");
                }

        }

        @DataProvider
        public Object[][] scenarios() {
                return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
        }


        @AfterClass(
                alwaysRun = true
        )
        public void tearDownClass() {
                if (this.testNGCucumberRunner != null) {
                        this.testNGCucumberRunner.finish();
                }
        }

        private ThreadLocal<String> testName = new ThreadLocal<>();

        @BeforeMethod(alwaysRun = true)
        public void setTestName(Method method, Object[] row) {
                String name = row[0].toString();
                testName.set(name);
        }

        @Override
        public String getTestName() {
                return testName.get();
        }

}


