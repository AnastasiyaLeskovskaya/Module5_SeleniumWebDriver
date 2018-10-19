package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.Mail;

import java.util.Collection;

public class SentPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/sent/";

    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='b-nav_folders']/div/div[2]/a")
    private WebElement sentItems;

    @FindBy(xpath = "//div[@class='b-datalist__item__info']")
    private WebElement waitFor;

    @FindBy(xpath = "//*[@id='b-letters']//div[@class='b-datalist__item__addr']")
    private WebElement mail;

    @FindBy(css = "div.b-toolbar__btn.b-toolbar__btn_.b-toolbar__btn_grouped_last")
    private WebElement spamButton;

    @Override
    public SentPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public void openMail(){
        waiterForElementClickable(mail);
        mail.click();
    }

    public SpamFolderPage spamButtonClick(){
        waiterForElementVisible(spamButton);
        spamButton.click();
        return new SpamFolderPage(driver);
    }

    public boolean isMailExistInSentFolder(Mail mail) {
        Collection<WebElement> sentList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
            for (WebElement element : sentList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(mail.getSubject()+mail.getText_field()))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(mail.getAddressee_mail_field()))) {
                    element.click();
                    return true;
                }
            }
        return false;
    }
}


