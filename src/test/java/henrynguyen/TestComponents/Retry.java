package henrynguyen.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int retryCount = 0;
    int maxRetryCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retryCount < maxRetryCount)
        {
            retryCount++;
            return true;
        }
        return false;
    }
}
