package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utilities.DriverFactory;
import utilities.ScreenshotUtil;
import utilities.ExtentManager;
import utilities.LoggerHelper;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        DriverFactory.getDriver(); // Only open browser here!
        ExtentManager.createTest(scenario.getName());
        LoggerHelper.logger.info(">>> Starting Scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        try {
            // Take screenshot after EVERY step and add to Extent report
            String screenshotPath = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), scenario.getName());
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            LoggerHelper.logger.warn("Could not take screenshot after step: " + e.getMessage());
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerHelper.logger.error("Scenario FAILED: " + scenario.getName());
            ExtentManager.getTest().fail("Scenario failed.");
        } else {
            LoggerHelper.logger.info("Scenario PASSED: " + scenario.getName());
            ExtentManager.getTest().pass("Scenario passed.");
        }
        DriverFactory.quitDriver();
        ExtentManager.flush();
    }
}
