package selenium.steps;

import org.openqa.selenium.WebDriver;
import selenium.objects.Contact;
import selenium.driver.DriverSingleton;
import selenium.objects.User;
import selenium.pages.*;

public class Steps {

    private WebDriver driver;

    // private final Logger logger = LogManager.getRootLogger();

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver()
    {
        DriverSingleton.closeDriver();
    }

    public void login(User user)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage().login(user);
    }

    public boolean isHomePageOpened(String mailAddress)
    {
        HomePage homePage = new HomePage(driver);
        return homePage.homePageStatus(mailAddress);
    }


    public void createMail(String  addresseeMail, String  subject, String text )
    {
        HomePage homePage = new HomePage(driver);
        homePage.writeMailButtonClick();
        homePage.fillAddresseeField(addresseeMail);
        homePage.fillSubjectField(subject);
        homePage.fillFrame(text);
        homePage.saveButtonClick();
    }

    public boolean verifyDraft(String  addresseeMail, String  subject, String text){
        DraftManager draftManager = new DraftManager(driver);
        draftManager.openPage();
        return draftManager.verifyDraftFolder(addresseeMail, subject, text);
    }

    public void sendMail(){
        DraftManager draftManager = new DraftManager(driver);
        draftManager.sendButtonClick();
    }

    public boolean verifySentFolder(String  addresseeMail, String  subject, String text){
        SentManager sentManager = new SentManager(driver);
        sentManager.openPage();
        sentManager.sentItemsClick();
        return sentManager.verifySentFolder(addresseeMail,subject,text);
    }

    public void logOff(){
        HomePage homePage = new HomePage(driver);
        homePage.logOffLinkClick();
    }

    public void fillAddContact(Contact contact){
        HomePage homePage = new HomePage(driver);
        ContactsPage contactsPage = homePage.contactsButtonClick();
        AddContactsPage addContactsPage = contactsPage.addButtonClick();
        addContactsPage.fillContactsFields(contact).saveButtonClick();
    }

    public boolean verifyContactInAllContactsFolder(Contact contact){
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.openPage();
        contactsPage.goToAllContactFolder();
        return contactsPage.verifyAllContactFolder(contact);
    }

    public void moveMailInSpam(){
       InboxFolder inboxFolder = new InboxFolder(driver);
       inboxFolder.openMail();
       inboxFolder.spamButtonClick();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
