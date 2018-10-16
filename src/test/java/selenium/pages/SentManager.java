package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class SentManager extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/sent/";

    public SentManager(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='b-nav_folders']/div/div[2]/a")
    private WebElement sentItems;

    @FindBy(xpath = "//div[@class='b-datalist__item__info']")
    private WebElement waitFor;

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        // logger.info("Login page opened");
    }

    public void sentItemsClick() {
        sentItems.click();
    }

    public boolean verifySentFolder(String addresseeMail, String subject, String text) {
        Collection<WebElement> sentList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
      //  if (!sentList.isEmpty()){
            for (WebElement element : sentList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject+text))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeMail))) {
                    element.click();
                    return true;
                }
            }
       // }
        return false;
    }
}


