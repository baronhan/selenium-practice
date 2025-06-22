package henrynguyen.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import henrynguyen.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

    @Override
    public void onTestStart(ITestResult result)
    {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique thread id -> test object
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        extentTest.get().fail(result.getThrowable());

        //Screenshot
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = null;

        try {
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context)
    {
        //Flush results to the HTML file
        extent.flush();
    }
}
