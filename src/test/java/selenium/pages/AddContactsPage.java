package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


    public AddContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AddContactsPage fillContactsFields(Contact contact){
        firstnameField.sendKeys(contact.getFirstname());
        lastnameField.sendKeys(contact.getLastName());
        companyField.sendKeys(contact.getCompanyName());
        emailField.sendKeys(contact.getEmail());
        return this;
    }

    public AddContactsPage fillContactsFieldsUsingKeyboard(Contact contact){
        Actions actions = new Actions(driver);
        actions.click(firstnameField).sendKeys(contact.getFirstname()).build().perform();
        actions.sendKeys(lastnameField, contact.getLastName()).build().perform();
        actions.sendKeys(emailField, contact.getEmail()).build().perform();
        return this;
    }

    public AddContactsPage saveButtonClick(){
        saveButton.click();
        return this;
    }



    @Override
    public AddContactsPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

}
