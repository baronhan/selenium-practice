package henrynguyen.AbtractComponents;

import henrynguyen.pageobjects.CartPage;
import henrynguyen.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    @FindBy(css = "*[routerlink*='cart']")
    WebElement cart;

    @FindBy(css = "*[routerlink*='order']")
    WebElement order;

    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(By findBy)
    {
//       Thread.sleep(1000)
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public CartPage goToCartPage()
    {
        cart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage()
    {
        order.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
