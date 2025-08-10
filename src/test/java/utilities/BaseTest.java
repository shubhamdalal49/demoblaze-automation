package utilities;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest test;

    @Before
    public void setUp() {
        // driver = DriverFactory.getDriver();  // Only gets if not exists
        // int implicitWait = ConfigReader.getInt("implicitWait");
        // driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));
        test = ExtentManager.createTest(getClass().getSimpleName());
        LoggerHelper.logger.info("=== Test Started: " + getClass().getSimpleName() + " ===");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver(); // Only closes if exists; always sets to null!
        ExtentManager.getExtentReports().flush();
        LoggerHelper.logger.info("=== Test Ended: " + getClass().getSimpleName() + " ===");
    }
}
