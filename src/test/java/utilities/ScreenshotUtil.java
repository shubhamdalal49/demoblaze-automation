package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String scenarioName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String path = "target/screenshots/" + scenarioName + "-" + timestamp + ".png";
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (Exception e) {
            LoggerHelper.logger.error("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}
