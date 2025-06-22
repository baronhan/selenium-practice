package henrynguyen.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject()
    {
        // 1. Set the HTML output path
        String path = System.getProperty("user.dir") + "//reports//index.html";

        // 2. Create and configure the visual layout of the report
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Result");
        reporter.config().setDocumentTitle("Test Result");

        // 3.Create ExtentReports object and attach the reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Henry Nguyen");

        return extent;
    }
}
