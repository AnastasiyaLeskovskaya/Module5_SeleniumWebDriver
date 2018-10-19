package selenium.steps;

import org.openqa.selenium.WebDriver;
import selenium.driver.DriverSingleton;
import selenium.objects.Contact;
import selenium.objects.Mail;
import selenium.objects.User;
import selenium.pages.*;

public class Steps {

    private WebDriver driver;

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

    public boolean isHomePageOpened(User user) {
        HomePage homePage = new HomePage(driver);
        return homePage.homePageStatus().equals(user.getMailAddress());
    }

    public void createMail(Mail mail)
    {
        HomePage homePage = new HomePage(driver);
        homePage.writeMailButtonClick();
        homePage.fillAddresseeField(mail.getAddressee_mail_field());
        homePage.fillSubjectField(mail.getSubject());
        homePage.fillFrame(mail.getText_field());
    }

    public void saveMail(){
        HomePage homePage = new HomePage(driver);
        homePage.saveButtonClick();
    }

    public boolean isMailInDraftFolder(Mail mail){
        DraftFolderPage draftManager = new DraftFolderPage(driver);
        draftManager.openPage();
        return draftManager.equalsMails(mail);
    }

    public void sendMailFromDraft(){
        DraftFolderPage draftManager = new DraftFolderPage(driver);
        draftManager.sendButtonClick();
    }

    public boolean isMailExistInSentFolder(Mail mail){
        SentPage sentPage = new SentPage(driver);
        sentPage.openPage();
        return sentPage.isMailExistInSentFolder(mail);
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

    public void fillAddContactUsingKeyboard(Contact contact){
        HomePage homePage = new HomePage(driver);
        ContactsPage contactsPage = homePage.contactsButtonClick();
        AddContactsPage addContactsPage = contactsPage.addButtonClick();
        addContactsPage.fillContactsFieldsUsingKeyboard(contact).saveButtonClick();
    }



    public boolean isContactExistInAllContactsFolder(Contact contact){
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.openPage();
        return contactsPage.isContactExistInAllContactsFolder(contact);//verifyAllContactFolder(contact);
    }

    public void moveMailInSpam(){
        SentPage sentPage = new SentPage(driver);
        sentPage.openPage().openMail();
        sentPage.spamButtonClick();
    }

    public void moveMailToSpamWithDragNDrop(Mail mail){
        DraftFolderPage draftManager =  new DraftFolderPage(driver);
        draftManager.openPage();
        draftManager.dragNDropMailInSpamFolder(mail);
        SpamFolderPage spamFolderPage = new SpamFolderPage(driver);
        spamFolderPage.isMailExistInSpamFolder(mail);
    }


    public boolean verifySpamFolder(Mail mail){
       SpamFolderPage spamFolderPage = new SpamFolderPage(driver);
       spamFolderPage.openPage();
       return spamFolderPage.isMailExistInSpamFolder(mail);
    }

}
