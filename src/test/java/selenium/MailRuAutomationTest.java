package selenium;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import selenium.steps.Steps;

public class MailRuAutomationTest {
    private Steps steps;
    private final String USERNAME = "leskovskaya_test";
    private final String PASSWORD = "test123";
    private final String MAIL_ADDRESS = "leskovskaya_test@mail.ru";
    private final String ADDRESSEE_MAIL_FIELD = "test@test.ru";
    private final String SUBJECT_FIELD = "test";
    private final String TEXT_FIELD = "Text";

    @BeforeClass (description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    // Login to the mail box, assert, that the login is successful.
    @Test
    public void isLoginSuccessful()
    {
        steps.loginMailRu(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isHomePageOpened(MAIL_ADDRESS), "Home page was not open");
    }

    //Create a new mail (fill addressee, subject and body fields), save the mail as a draft, Verify, that the mail presents in ‘Drafts’ folder.
    @Test(dependsOnMethods = "isLoginSuccessful")
    public void verifyDraftsFolder()
    {
        steps.createMail(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD);
        Assert.assertTrue(steps.verifyDraft(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is not in the 'Drafts' folder.");
    }

    //Send the mail. Verify, that the mail disappeared from ‘Drafts’ folder. Verify, that the mail is in ‘Sent’ folder.
    @Test(dependsOnMethods = "verifyDraftsFolder")
    public void verifySendingMail()
    {
        steps.sendMail();
        Assert.assertFalse(steps.verifyDraft(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is  in the 'Drafts' folder.");
        Assert.assertTrue(steps.verifySentFolder(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is  in the 'sent' folder.");
        steps.logOff();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
