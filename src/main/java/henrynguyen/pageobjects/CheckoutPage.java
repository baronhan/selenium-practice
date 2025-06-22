package henrynguyen.pageobjects;

import henrynguyen.AbtractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countrySelector;

    @FindBy(css = "section[class*='ng-star-inserted']")
    List<WebElement> countryList;

    @FindBy(css = ".action__submit")
    WebElement submitButton;

    By countrySelections = By.cssSelector("section[class*='ng-star-inserted']");

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 8000);");
    }

    public void sendCountryKey(String countryKey)
    {
        countrySelector.sendKeys(countryKey);
        waitForElementToAppear(countrySelections);
    }

    public List<WebElement> getCountries()
    {
        return countryList;
    }

    public WebElement verifyCountryValue(String countryValue)
    {
        WebElement country = countryList.stream()
                .filter(countrySelection -> countrySelection.getText().equals(countryValue)).findFirst().orElse(null);
        return country;
    }

    public void chooseCountry(WebElement country)
    {
        country.click();
    }

    public ConfirmationPage goToConfirmationrPage()
    {
        submitButton.click();
        ConfirmationPage orderPage = new ConfirmationPage(driver);
        return orderPage;
    }

}
