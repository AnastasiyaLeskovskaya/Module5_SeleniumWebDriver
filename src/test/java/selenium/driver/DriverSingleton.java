package selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DriverSingleton {
    private static WebDriver driver;
   // private static final Logger logger = LogManager.getRootLogger();
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_EXE_PATH = "C:\\Users\\Anastasiya_Leskovska\\WebDriver\\chromedriver_win32\\chromedriver.exe";

    private DriverSingleton(){};

    public static WebDriver getDriver(){
        if (null == driver){
            System.out.println("ChromeDrever Hello");
            System.setProperty(CHROME_DRIVER, CHROME_DRIVER_EXE_PATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println(driver.getTitle());
            //logger.info("Browser started");
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}

