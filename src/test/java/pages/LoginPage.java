package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement submitButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeLabel;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginModal() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getWelcomeText() {
        return wait.until(ExpectedConditions.visibilityOf(welcomeLabel)).getText();
    }
}
