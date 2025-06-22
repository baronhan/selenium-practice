package henrynguyen.tests;

import henrynguyen.TestComponents.BaseTest;
import henrynguyen.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
    public String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products =   productCatalogue.getProductList();

        WebElement prod = productCatalogue.getProductByName(input.get("productName"));

        productCatalogue.addProductToCart(prod);

        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckOut();

        checkoutPage.sendCountryKey("Viet");

        List<WebElement> countrySelections = checkoutPage.getCountries();

        WebElement country = checkoutPage.verifyCountryValue("Vietnam");

        checkoutPage.chooseCountry(country);

        ConfirmationPage orderPage = checkoutPage.goToConfirmationrPage();

        String confirmMessage = orderPage.getTextFromHeadTitle();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryCheck(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("henry111@gmail.com", "Henry180803@");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir") + "//src//test//java//henrynguyen//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

}

//    Using Array
//    @DataProvider
//    public Object[][] getData()
//    {
//
//        return new Object[][] {{"henry111@gmail.com", "Henry180803@", "ZARA COAT 3"}, {"henry111@gmail.com", "Henry180803@", "ADIDAS ORIGINAL"}};
//    }


//     Using Hash Map
//     HashMap<String, String> map = new HashMap<String, String>();
//     map.put("email", "henry111@gmail.com");
//     map.put("password", "Henry180803@");
//     map.put("productName", "ZARA COAT 3");
//
//     HashMap<String, String> map1 = new HashMap<String, String>();
//     map1.put("email", "henry111@gmail.com");
//     map1.put("password", "Henry180803@");
//     map1.put("productName", "ADIDAS ORIGINAL");