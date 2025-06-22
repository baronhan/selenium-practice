package henrynguyen.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {
    WebDriver driver;
    @FindBy(css = "tr[class*='inserted'] td:nth-child(3)")
    List<WebElement> products;

    public OrderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName)
    {
        Boolean match = products.stream().anyMatch(product ->
                product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
