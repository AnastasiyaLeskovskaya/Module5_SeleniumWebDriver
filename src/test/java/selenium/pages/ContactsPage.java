package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.Contact;

public class ContactsPage extends AbstractPage {
    private final String BASE_URL = "https://e.mail.ru/addressbook";

    @FindBy(xpath = "//div[@id='js-add-contact']")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id='leftcol__placeholder']//span[text()='Все контакты']")
    private WebElement allContactFolder;

    public AddContactsPage addButtonClick(){
        addButton.click();
        return new AddContactsPage(driver);
    }

    public ContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isContactExistInAllContactsFolder(Contact contact) {
        WebElement element = driver.findElement(By.xpath("//span[text()='"+contact.getLastName()+" "+contact.getFirstname()+"']"));
        return  element.isDisplayed();
    }

    @Override
    public ContactsPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

}
