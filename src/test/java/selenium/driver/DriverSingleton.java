package selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    // private static final Logger logger = LogManager.getRootLogger();
//    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
//    private static final String CHROME_DRIVER_EXE_PATH = "C:\\Users\\Anastasiya_Leskovska\\WebDriver\\chromedriver_win32\\chromedriver.exe";
    private static  String chrome_driver;
    private static String chrome_driver_exe_path;
    private DriverSingleton(){};

    public static WebDriver getDriver(){
        if (null == driver){
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(new File("config/config.ini")));
            }catch (IOException e){
                System.out.println("Config file not found");
            }
            chrome_driver = properties.getProperty("CHROME_DRIVER");
            chrome_driver_exe_path = properties.getProperty("CHROME_DRIVER_EXE_PATH");
            System.setProperty(chrome_driver, chrome_driver_exe_path);
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

