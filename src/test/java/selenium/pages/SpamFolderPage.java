package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.Mail;

import java.util.Collection;

public class SpamFolderPage extends AbstractPage {
    private final String BASE_URL = "https://e.mail.ru/messages/spam/";


    public SpamFolderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isMailExistInSpamFolder(Mail mail){
        Collection<WebElement> spamList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        for (WebElement element : spamList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().
                        equals(mail.getSubject()+mail.getText_field())))
                return true;
            }
        return false;
    }


    @Override
    public SpamFolderPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

}
