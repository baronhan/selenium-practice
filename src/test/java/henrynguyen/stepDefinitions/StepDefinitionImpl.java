package henrynguyen.stepDefinitions;

import henrynguyen.TestComponents.BaseTest;
import henrynguyen.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage orderPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        WebElement prod = productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(prod);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName)
    {
        cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        checkoutPage = cartPage.goToCheckOut();

        checkoutPage.sendCountryKey("Viet");

        List<WebElement> countrySelections = checkoutPage.getCountries();

        WebElement country = checkoutPage.verifyCountryValue("Vietnam");

        checkoutPage.chooseCountry(country);

        orderPage = checkoutPage.goToConfirmationrPage();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_is_displayed_on_Confirmation_Page(String string)
    {
        String confirmMessage = orderPage.getTextFromHeadTitle();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    @Then("{string} message is displayed")
    public void messsage_is_displayed(String string)
    {
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }
}