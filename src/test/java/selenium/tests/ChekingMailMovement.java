package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.steps.Steps;

public class ChekingMailMovement {

        private Steps steps;

        private final String USERNAME = "leskovskaya_test";
        private final String PASSWORD = "test123";
        private final String MAIL_ADDRESS = "leskovskaya_test@mail.ru";
        private final String ADDRESSEE_MAIL_FIELD = "Команда Mail.ru";
        private final String SUBJECT_FIELD = "Узнайте о супервозможностях Почты Mail.Ru";


        @BeforeClass(description = "Init browser")
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

        //  Create a new mail (fill addressee, subject and body fields), save the mail as a draft, Verify, that the mail presents in ‘Drafts’ folder.
        @Test(dependsOnMethods = "isLoginSuccessful")
        public void verifyInboxFolder()
        {
            steps.checkInboxFolder(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD);
           // Assert.assertTrue(steps.verifyDraft(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is not in the 'Drafts' folder.");
        }
//
//        //move mail in basket. Verify, that the mail is in ‘Basket’ folder.
//        @Test(dependsOnMethods = "verifyDraftsFolder")
//        public void verifyBasketFolder()
//        {
//            steps.moveMailToBasket();
////            Assert.assertFalse(steps.verifyDraft(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is  in the 'Drafts' folder.");
////            Assert.assertTrue(steps.verifySentFolder(ADDRESSEE_MAIL_FIELD, SUBJECT_FIELD, TEXT_FIELD), "The mail is  in the 'sent' folder.");
////            steps.logOff();
//        }

        @AfterClass(description = "Stop Browser")
        public void stopBrowser()
        {
            steps.closeDriver();
        }
    }


