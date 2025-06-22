package henrynguyen.pageobjects;

import henrynguyen.AbtractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LandingPage extends AbstractComponent {

    public WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@id='userPassword']")
    WebElement userPassword;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
