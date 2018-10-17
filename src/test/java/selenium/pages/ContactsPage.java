package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.Contact;

import java.util.Collection;

public class ContactsPage extends AbstractPage {
    private final String BASE_URL = "https://e.mail.ru/addressbook";

    @FindBy(xpath = "//div[@id='js-add-contact']")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id='leftcol__placeholder']//span[text()='Все контакты']")
    private WebElement allContactFolder;

    public AddContactsPage addButtonClick(){
        System.out.println(driver.getCurrentUrl());
        addButton.click();

        return new AddContactsPage(driver);
    }

    public ContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean verifyAllContactFolder(Contact contact){
        Collection<WebElement> contactList = driver.findElements(By.xpath("//*[@id='addressbook_page_view']//div[@class='messageline contactline']"));
        for (WebElement element : contactList) {
            if ((element.findElement(By.xpath("//*[@id='addressbook_page_view']//span[@class='contactline__body__item contactline__body__item_name']/span")).
                    getText().replaceAll("\\s","").equalsIgnoreCase(contact.getLastName()+contact.getFirstname()))
                    && (element.findElement(By.xpath("//*[@id='addressbook_page_view']//span[@class='contactline__body__item contactline__body__item_emails']/span")).
                    getText().equalsIgnoreCase(contact.getEmail()))) {
                return true;
            }
        }
        return false;
    }

    public ContactsPage goToAllContactFolder(){
        allContactFolder.click();
        return new ContactsPage(driver);
    }

    @Override
    public ContactsPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

}
