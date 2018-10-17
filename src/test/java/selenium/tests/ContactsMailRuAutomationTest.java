package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.objects.Contact;
import selenium.objects.User;
import selenium.steps.Steps;
import java.util.Properties;

public class ContactsMailRuAutomationTest {

    private Steps steps;
    private User user;


    @BeforeClass(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
        user = new User();
    }

    // Login to the mail box, assert, that the login is successful.
    @Test
    public void isLoginSuccessful()
    {
        steps.login(user);
        Assert.assertTrue(steps.isHomePageOpened(user.getMailAddress()), "Home page was not open");
    }

    //  Create a new mail (fill addressee, subject and body fields), save the mail as a draft, Verify, that the mail presents in ‘Drafts’ folder.
    @Test(dependsOnMethods = "isLoginSuccessful")
    public void verifyAddContacts()
    {
        Contact contact = new Contact();
        steps.fillAddContact(contact);
        Assert.assertTrue(steps.verifyContactInAllContactsFolder(contact), "The contact is not in the 'All contacts' folder.");
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}






