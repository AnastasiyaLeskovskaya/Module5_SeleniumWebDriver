package selenium.steps;

import org.openqa.selenium.WebDriver;
import selenium.driver.DriverSingleton;
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

    public void loginMailRu(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isHomePageOpened(String mailAddress)
    {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.homePageStatus(mailAddress);
    }


    public void createMail(String  addresseeMail, String  subject, String text )
    {
        HomePage homePage = new HomePage(driver);
        homePage.writeMailButtonClick();
        homePage.fillAddresseeField(addresseeMail);
        homePage.fillSubjectField(subject);
        homePage.fillFrame(text);
        homePage.saveButtonClick();
//        WebDriverWait wait = new WebDriverWait(driver, 50);
////       saveButton  = wait.until(ExpectedConditions.visibilityOf(saveButton));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //System.out.println(driver.getCurrentUrl());
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

    public void checkInboxFolder(String  addresseeMail, String  subject){
        InboxFolder inboxFolder  = new InboxFolder(driver);
        inboxFolder.finderMail(addresseeMail, subject);


    }

    public void logOff(){
        HomePage homePage = new HomePage(driver);
        homePage.logOffLinkClick();
    }
}
