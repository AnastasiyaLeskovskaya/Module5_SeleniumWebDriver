package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.objects.Mail;
import selenium.utils.Screenshoter;

import java.util.Collection;

public class DraftFolderPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/drafts/";

    @FindBy(xpath = "//div[@data-id='0:15396926720000000147:500001']//div[@class='js-item-checkbox b-datalist__item__cbx']")
    private WebElement mailCheckbox;

    @FindBy(xpath = "//div[@class='b-toolbar__item']//span[text()='Переместить']")
    private WebElement moveButton;

    @FindBy(xpath = "//div[@class='b-datalist__item__info']")
    private WebElement draftList;

    @FindBy(xpath = "//*[@id='b-nav_folders']//span[text()='Спам']")
    private WebElement target;

    public DraftFolderPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public boolean equalsMails(Mail mail) {//(String addresseeMail, String subject, String text) {
        Collection<WebElement> draftList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!draftList.isEmpty()){
            for (WebElement element : draftList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().
                        equals(mail.getSubject()+mail.getText_field())))
                    element.click();
                return true;
            }
        }
        return false;
    }

    public DraftFolderPage dragNDropMailInSpamFolder(Mail mail) {
        WebElement element = driver.findElement(By.xpath("//*[@id='b-letters']//a[@data-subject='"+mail.getSubject()+"']"));
        Screenshoter.takeScreenshot();
        new Actions(driver).dragAndDrop(element, target).build().perform();
        Screenshoter.takeScreenshot();
        return this;
    }

    public DraftFolderPage sendButtonClick(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name='send']")));
        WebElement sendButton = driver.findElement(By.xpath("//div[@data-name='send']"));
        sendButton.click();
        return this;
    }

    @Override
    public DraftFolderPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
