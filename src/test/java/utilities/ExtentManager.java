package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkReport.html");
            spark.config().setReportName("Demoblaze Automation - Interactive Report");
            spark.config().setDocumentTitle("Demoblaze Automation Results");

            
            spark.config().setTheme(Theme.STANDARD);
            // Agar light mode chahiye ho, use: spark.config().setTheme(Theme.STANDARD);

            // ==== (Optional) Add custom logo ====
            spark.config().setTimelineEnabled(true);       // Timeline for execution
            spark.config().setCss("body { font-family: Verdana; }"); // Custom CSS

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Platform", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome 138");
            extent.setSystemInfo("Base URL", ConfigReader.get("baseUrl"));
            extent.setSystemInfo("Test Author", "Your Name");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        test = getExtentReports().createTest(name);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }
}
