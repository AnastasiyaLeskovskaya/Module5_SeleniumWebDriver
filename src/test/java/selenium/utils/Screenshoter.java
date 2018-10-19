package selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import selenium.driver.DriverSingleton;

import java.io.File;
import java.io.IOException;

public class Screenshoter extends TestListenerAdapter {
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure(iTestResult);
        Screenshoter.takeScreenshot();
    }

    public static void takeScreenshot() {

        WebDriver driver = DriverSingleton.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
        } catch (IOException e) {
            System.out.println("Failed to make screenshot");
        }
    }
}
