package common.data.setUp;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CommonDataSetUp1 {

    protected WebDriver driver; // Access modifier to protected for inheritance
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;

    @BeforeTest
    public void setUp(ITestContext context) {
        String browserName = "chrome";
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                System.out.println("Invalid browser name specified.");
                return;
            }

            driver.manage().window().maximize();
            // Create a new test in the extent report with the name of the test method
            extentTest = extentReport.createTest(context.getName());
            // Assign the author to the test
            String author = context.getCurrentXmlTest().getParameter("Author");
            extentTest.assignAuthor(author);
        } catch (Exception e) {
            System.out.println("Exception occurred while setting up WebDriver: " + e.getMessage());
        }
    }

    @AfterMethod
    public void checkStatus(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                // Capture screenshot and attach it to the extent report in case of failure
                String screenshotPath = captureScreenShot(result.getMethod().getMethodName() + "_Failure.png");
                extentTest.fail("Test Failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                
                System.out.println("Test " + result.getMethod().getMethodName() + " failed.");
                
                // Include stack trace in extent report
                Throwable throwable = result.getThrowable();
                if (throwable != null) {
                    extentTest.fail(throwable);
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test Passed");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while capturing screenshot: " + e.getMessage());
        }
    }

    @BeforeSuite
    public void initializeExtentReporter() {
        extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
        extentReport.attachReporter(spark);

        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Host name", System.getProperty("localhost"));
        extentReport.setSystemInfo("User name", System.getProperty("user.name"));
    }

    @AfterSuite
    public void generateExtentReport() throws IOException {
        extentReport.flush();
        Desktop.getDesktop().browse(new File("report.html").toURI());
    }

    public String captureScreenShot(String fileName) {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File sourceFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("./screenshot/" + fileName);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Screenshot saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
