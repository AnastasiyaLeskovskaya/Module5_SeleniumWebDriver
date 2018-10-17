package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;

public class InboxFolder extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/sent/";


    @FindBy(xpath = "//*[@id='b-letters']//div[@class='b-datalist__item__addr']")
    private WebElement mail;


    @FindBy(css = "div.b-toolbar__btn.b-toolbar__btn_.b-toolbar__btn_grouped_last")
    private WebElement spamButton;

    public InboxFolder(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openMail(){
       waiterForElementClickable(mail);
       mail.click();
    }
    public void spamButtonClick(){
        waiterForElementVisible(spamButton);
        spamButton.click();
    }


//    public boolean finderMail(String addresseeMail, String subject){
//        Collection<WebElement> inboxList = driver.findElements(By.xpath("//div[@data-bem='b-datalist__item']"));
//        if (!inboxList.isEmpty()){
//            for (WebElement element : inboxList) {
//                System.out.println(element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject));
//                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject))
//                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr")).getText().equalsIgnoreCase(addresseeMail))) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    @Override
    public InboxFolder openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
        // logger.info("Login page opened");
    }

}
