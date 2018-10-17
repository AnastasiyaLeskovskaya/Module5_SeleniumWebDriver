package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.objects.Mail;
import selenium.objects.User;
import selenium.steps.Steps;

import java.util.Properties;

public class MovingMailInSpamFolderTest {

    private  Steps steps;
    private User user;
    private Mail mail;

    @BeforeClass (description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
        user = new User();
        mail = new Mail();
    }

    // Login to the mail box, assert, that the login is successful.
    @Test
    public void isLoginSuccessful()
    {
        User user = new User();
        steps.login(user);
        Assert.assertTrue(steps.isHomePageOpened(user.getMailAddress()), "Home page was not open");
    }

    //Create a new mail (fill addressee, subject and body fields), save the mail as a draft, Verify, that the mail presents in ‘Drafts’ folder.
    @Test(dependsOnMethods = "isLoginSuccessful")
    public void verifyDraftsFolder()
    {
        steps.createMail(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field());
        Assert.assertTrue(steps.verifyDraft(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field()), "The mail is not in the 'Drafts' folder.");
    }

    @Test(dependsOnMethods = "verifyDraftsFolder")
    public void verifySendingMail()
    {
        steps.sendMail();
        Assert.assertFalse(steps.verifyDraft(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field()), "The mail is  in the 'Drafts' folder.");
        Assert.assertTrue(steps.verifySentFolder(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field()), "The mail is  in the 'sent' folder.");
    }

    @Test(dependsOnMethods = "verifyDraftsFolder")
    public void verifySpamFolder()
    {
        steps.moveMailInSpam();
//        steps.createMail(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field());
//        Assert.assertTrue(steps.verifyDraft(mail.getAddressee_mail_field(),mail.getSubject(),mail.getText_field()), "The mail is not in the 'Drafts' folder.");
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}

