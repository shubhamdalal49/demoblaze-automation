package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        int explicitWait = ConfigReader.getInt("explicitWait");
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(explicitWait));
        PageFactory.initElements(driver, this);
    }
}
