package henrynguyen.tests;

import henrynguyen.TestComponents.BaseTest;
import henrynguyen.TestComponents.Retry;
import henrynguyen.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

public class ErrorValidation extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void errorValidation(){
        landingPage.loginApplication("henry111@gmail.com", "Henry180803");
        Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
    }
}
