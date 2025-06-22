package henrynguyen.pageobjects;

import henrynguyen.AbtractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;


    public Boolean verifyProductDisplay(String productName) {
        Boolean match = cartProducts.stream().anyMatch(cartProduct ->
                cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckOut()
    {
        checkoutButton.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}
