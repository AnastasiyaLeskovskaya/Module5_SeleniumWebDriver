package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class DraftManager extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/drafts/";

    @FindBy(xpath = "//div[@data-id='0:15396926720000000147:500001']//div[@class='js-item-checkbox b-datalist__item__cbx']")
    private WebElement mailCheckbox;

    @FindBy(xpath = "//div[@class='b-toolbar__item']//span[text()='Переместить']")
    private WebElement moveButton;

    public DraftManager(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean verifyDraftFolder(String addresseeMail, String subject, String text) {
        Collection<WebElement> draftList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!draftList.isEmpty()){
            for (WebElement element : draftList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject+text))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeMail))) {
                    element.click();
                    return true;
                }
            }
        }
        return false;
    }

    public void sendButtonClick(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name='send']")));
        WebElement sendButton = driver.findElement(By.xpath("//div[@data-name='send']"));
        sendButton.click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message-sent__title']")));
    }

    public void clickMail(){
        mailCheckbox.click();
    }

    public void moveButtonClick(){
        moveButton.click();
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }




}
