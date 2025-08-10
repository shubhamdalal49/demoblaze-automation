package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import pages.*;
import utilities.*;

import java.time.Duration;

public class E2EPurchaseFlowSteps {
    WebDriver driver;
    SignUpPage signUpPage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    String username;
    String password = "Test@123";
    String product = "Samsung galaxy s6";

    @Given("user opens Demoblaze homepage")
    public void openHomePage() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.demoblaze.com/");
        LoggerHelper.logger.info("Homepage opened.");
        ExtentManager.getTest().info("Opened Demoblaze homepage.");
    }

    @When("user registers with a new username and logs in")
    public void registerAndLogin() throws InterruptedException {
        username = "user" + System.currentTimeMillis();
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);

        signUpPage.openSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.submitSignUp();

        // Accept registration alert
        Thread.sleep(1200);
        try { driver.switchTo().alert().accept(); } catch (Exception ignored) {}

        loginPage.openLoginModal();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLogin();

        // Accept login alert
        Thread.sleep(1200);
        try { driver.switchTo().alert().accept(); } catch (Exception ignored) {}

        LoggerHelper.logger.info("Registered and logged in: " + username);
        ExtentManager.getTest().info("User registered and logged in: " + username);
    }

    @When("user selects the {string} product")
    public void selectProduct(String prod) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prod)));
        productLink.click();
        Thread.sleep(1000);
        LoggerHelper.logger.info("Selected product: " + prod);
        ExtentManager.getTest().info("Selected product: " + prod);
    }

    @When("user adds the product to the cart")
    public void addProductToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
        addToCart.click();

        // Accept cart alert
        try { driver.switchTo().alert().accept(); } catch (Exception ignored) {}
        Thread.sleep(1200);
        LoggerHelper.logger.info("Product added to cart.");
        ExtentManager.getTest().pass("Product added to cart.");
    }

    @When("user places the order with valid details")
    public void placeOrder() {
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Always accept any leftover alert before cart actions:
        try { driver.switchTo().alert().accept(); } catch (NoAlertPresentException e) {}

        cartPage.openCart();
        cartPage.clickPlaceOrder();
        checkoutPage.enterDetails("Shubham Daal", "India", "Pune", "4242424242424242", "08", "2025");
        checkoutPage.clickPurchase();
        LoggerHelper.logger.info("Order placed.");
        ExtentManager.getTest().pass("Order placed.");
    }

    @Then("user should see a {string} confirmation")
    public void verifyPurchaseConfirmation(String expectedMsg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(checkoutPage.isThankYouDisplayed(), "Thank you confirmation not found.");
        LoggerHelper.logger.info("Order confirmation verified: " + expectedMsg);
        ExtentManager.getTest().pass("Order confirmation: " + expectedMsg);
    }
}
