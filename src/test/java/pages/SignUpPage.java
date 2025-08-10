package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {

    @FindBy(id = "signin2")
    private WebElement signUpButton;

    @FindBy(id = "sign-username")
    private WebElement usernameField;

    @FindBy(id = "sign-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement submitButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    public Alert waitForAlert() {
    return wait.until(ExpectedConditions.alertIsPresent());
}

    public void openSignUpModal() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }

    public void submitSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
