package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.objects.Contact;
import selenium.objects.User;
import selenium.steps.Steps;

public class OneCanCreateNewContactTest {

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
        Assert.assertTrue(steps.isHomePageOpened(user), "Home page was not open");
    }

    @Test()
    public void oneCanAddContacts()
    {
        steps.login(user);
        Contact contact = new Contact();
        steps.fillAddContact(contact);
        Assert.assertTrue(steps.isContactExistInAllContactsFolder(contact), "The contact is not in the 'All contacts' folder.");
    }

    // add contact using actions
    @Test()
    public void oneCanAddContactsUsing()
    {
        steps.login(user);
        Contact contact = new Contact();
        steps.fillAddContactUsingKeyboard(contact);
        Assert.assertTrue(steps.isContactExistInAllContactsFolder(contact), "The contact is not in the 'All contacts' folder.");
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






