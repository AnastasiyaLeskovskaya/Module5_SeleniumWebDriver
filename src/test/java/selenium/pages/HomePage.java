package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class HomePage extends SentManager {
    private final String BASE_URL = "https://e.mail.ru/messages/inbox/?back=1";

    @FindBy(xpath = "//span[@class = 'b-toolbar__btn__text b-toolbar__btn__text_pad']")
    private WebElement writeMailButton;

    @FindBy(xpath  = "//textarea[@class = 'js-input compose__labels__input']")
    private WebElement addresseeField;

    @FindBy(xpath  = "//input[@name ='Subject']")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@data-name='saveDraft']")
    private WebElement saveButton;

    @FindBy(tagName = "iframe")
    private WebElement iframeForTextField;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement bodyTextField;

    @FindBy(css="span[class='time']")
    private WebElement waitFor;

    @FindBy(id ="PH_logoutLink")
    private WebElement logOffLink;

    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void writeMailButtonClick()  {
        writeMailButton.click();
    }

    public void fillAddresseeField(String  addresseeMail)  {
        addresseeField.sendKeys(addresseeMail);
    }

    public void fillSubjectField(String  subject)  {
        subjectField.sendKeys(subject);
    }

    public void fillFrame(String  string)  {
        driver.switchTo().frame(iframeForTextField);
        bodyTextField.clear();
        bodyTextField.sendKeys(string);
        driver.switchTo().defaultContent();
    }

    public void saveButtonClick()  {
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(waitFor));
    }

    public void logOffLinkClick()  {
        logOffLink.click();
    }


    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }
}
