package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(id = "cartur")
    private WebElement cartButton;

    @FindBy(xpath = "//tbody//tr/td[2]")
    private WebElement productInCart;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public boolean isProductPresent(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(productInCart));
            return productInCart.getText().contains(productName);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
}
