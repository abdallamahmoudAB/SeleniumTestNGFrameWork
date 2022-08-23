package tests.pom.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.pom.factory.DriverManager;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
       return this.driver.get();
    }

    @BeforeMethod
    public void startDriver() {
        setDriver( new DriverManager().initializeDriver());
        System.out.println("CURRENT THREAD: "+ Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());

    }

    @AfterMethod
    public void quitDriver(ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: "+ Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
        if (result.getStatus() == ITestResult.FAILURE) {
            File destinationFile = new File("scr" + File.separator + result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destinationFile);
        }
        getDriver().quit();

    }

    private void takeScreenshot(File destinationFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destinationFile);
    }
}
