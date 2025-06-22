package henrynguyen.pageobjects;

import henrynguyen.AbtractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = "*[routerlink*='cart']")
    WebElement cart;

    By productsBy = By.cssSelector(".mb-3");
    By toastContainer = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ng-animating");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(WebElement prod)
    {
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        waitForElementToAppear(toastContainer);
        waitForElementToDisappear(spinner);
    }
}
