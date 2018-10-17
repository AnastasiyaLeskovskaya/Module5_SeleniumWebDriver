package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(id = "PH_user-email")
    private WebElement linkLoggedInUser;

    @FindBy(xpath = "//span[@bem-id='105']//span")
    private WebElement contactsButton;

    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public DraftManager writeMailButtonClick()  {
        writeMailButton.click();
        return new DraftManager(driver);
    }

    public HomePage fillAddresseeField(String  addresseeMail)  {
        addresseeField.sendKeys(addresseeMail);
        return this;
    }

    public HomePage fillSubjectField(String  subject)  {
        subjectField.sendKeys(subject);
        return this;
    }

    public HomePage fillFrame(String  string)  {
        driver.switchTo().frame(iframeForTextField);
        bodyTextField.clear();
        bodyTextField.sendKeys(string);
        driver.switchTo().defaultContent();
        return this;
    }

    public HomePage saveButtonClick()  {
        saveButton.click();
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOf(waitFor));
        waiterForElementVisible(waitFor);
        return this;
    }
    public boolean homePageStatus(String mailAddress){
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOf(linkLoggedInUser));
        waiterForElementVisible(linkLoggedInUser);
        return mailAddress.equals(linkLoggedInUser.getText());
    }
    public LoginPage logOffLinkClick()  {
        logOffLink.click();
        return new LoginPage(driver);
    }

    public ContactsPage contactsButtonClick(){
        contactsButton.click();
        return new ContactsPage(driver);
    }
    @Override
    public HomePage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
