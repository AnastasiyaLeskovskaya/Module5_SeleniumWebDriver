package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import selenium.objects.Mail;
import selenium.objects.User;
import selenium.steps.Steps;

public class OneCanMovingMailInSpamFolderTest {

    private Steps steps;
    private User user;
    private Mail mail;

    @BeforeClass(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();

    }
    @BeforeMethod(description = "Init data")
    public void setData(){
        user = new User();
        mail = new Mail();
    }

    // Login to the mail box, assert, that the login is successful.
    @Test
    public void isLoginSuccessful()
    {
        steps.login(user);
        Assert.assertTrue(steps.isHomePageOpened(user), "Home page was not open");
    }

    //Create a new mail (fill addressee, subject and body fields), save the mail as a draft, Verify, that the mail presents in ‘Drafts’ folder.
    @Test()
    public void verifyDraftsFolder()
    {
        steps.login(user);
        steps.createMail(mail);
        steps.saveMail();
        Assert.assertTrue(steps.isMailInDraftFolder(mail), "The mail is not in the 'Drafts' folder.");
    }

    @Test()
    public void verifySendingMail()
    {
        steps.login(user);
        steps.createMail(mail);
        steps.saveMail();
        Assert.assertTrue(steps.isMailInDraftFolder(mail), "The mail is not in the 'Drafts' folder.");
        steps.sendMailFromDraft();
        Assert.assertTrue(steps.isMailExistInSentFolder(mail), "The mail is  in the 'Sent' folder.");
    }

    @Test()
    public void verifySpamFolder()
    {
        steps.login(user);
        steps.createMail(mail);
        steps.saveMail();
        steps.isMailInDraftFolder(mail);
        steps.sendMailFromDraft();
        steps.isMailExistInSentFolder(mail);
        steps.moveMailInSpam();
        Assert.assertTrue(steps.verifySpamFolder(mail), "The mail is not in the 'Spam' folder.");
    }

    @AfterMethod(description = "Stop Browser")
    public void logOff()
    {
        steps.logOff();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}

