package common.data.setUp;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class NopEcommerceTestListener extends CommonDataSetUp1 implements ITestListener {

	@Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	driver = new ChromeDriver();
    	if (driver != null) {
    		 captureScreenShot(result.getMethod().getMethodName() + "_Failure.png");
        } else {
            System.out.println("Driver is null, cannot capture screenshot.");
        }
    	driver.close();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
    }
}
