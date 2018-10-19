package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends SentPage {
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

    @FindBy(xpath = "//*[@id='b-toolbar__right']//span[text()='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[@id='b-nav_folders']//span[text()='Спам']")
    private WebElement spamFolderButton;


    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public DraftFolderPage writeMailButtonClick()  {
        writeMailButton.click();
        return new DraftFolderPage(driver);
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
        waiterForElementVisible(waitFor);
        return this;
    }

    public String homePageStatus(){
        waiterForElementVisible(linkLoggedInUser);
        highlightElement(linkLoggedInUser);
         return linkLoggedInUser.getText();
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
