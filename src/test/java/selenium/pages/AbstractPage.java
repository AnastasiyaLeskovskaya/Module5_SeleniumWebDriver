package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    private static int WAIT_FOR_ELENENT_SECONDS = 10;

    public abstract AbstractPage openPage();

    public AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void waiterForElementVisible(WebElement webElement){
        new WebDriverWait(driver, WAIT_FOR_ELENENT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void highlightElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid red'", element);
    }

    public void waiterForElementPresent(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELENENT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waiterForElementClickable(WebElement webElement){
        new WebDriverWait(driver, WAIT_FOR_ELENENT_SECONDS).until(ExpectedConditions.elementToBeClickable(webElement));

    }
}
