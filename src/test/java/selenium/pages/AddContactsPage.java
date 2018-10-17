package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.Contact;

public class AddContactsPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/addressbook/add";

    @FindBy(id = "firstname")
    private WebElement firstnameField;

    @FindBy(id = "lastname")
    private WebElement lastnameField;

    @FindBy(id = "company")
    private WebElement companyField;

    @FindBy(id = "emails_0")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='addressbook__toolbar']//span[text()='Сохранить']")
    private WebElement saveButton;

//    @FindBy(xpath = "//*[@id='leftcol__placeholder']//span[text()='Все контакты']")
//    private WebElement allContactFolder;


    public AddContactsPage fillContactsFields(Contact contact){
        firstnameField.sendKeys(contact.getFirstname());
        lastnameField.sendKeys(contact.getLastName());
        companyField.sendKeys(contact.getCompanyName());
        emailField.sendKeys(contact.getEmail());
        return this;
    }

    public AddContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void saveButtonClick(){
        saveButton.click();
    }

//    public ContactsPage goToAllContactFolder(){
//        allContactFolder.click();
//        return new ContactsPage(driver);
//    }


    @Override
    public AddContactsPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

}
