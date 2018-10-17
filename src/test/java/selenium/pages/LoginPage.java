package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.objects.User;

public class LoginPage extends AbstractPage {

    private final String BASE_URL = "https://mail.ru";

    @FindBy(id = "mailbox:login")
    private WebElement inputLogin;

    @FindBy(id = "mailbox:password")
    private WebElement inputPassword;

    @FindBy(css = "input.o-control")
    private WebElement buttonSubmit;

//    @FindBy(id = "PH_user-email")
//    private WebElement linkLoggedInUser;


    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

//    public HomePage login(String username, String password)  {
//        inputLogin.sendKeys(username);
//        inputPassword.sendKeys(password);
//        buttonSubmit.click();
//        return (new HomePage(driver));
//    }

    public HomePage login(User user)  {
        inputLogin.sendKeys(user.getLogin());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return (new HomePage(driver));
    }
//    public boolean homePageStatus(String mailAddress){
////        WebDriverWait wait = new WebDriverWait(driver, 30);
////        wait.until(ExpectedConditions.visibilityOf(linkLoggedInUser));
//        waiterForElementVisible(linkLoggedInUser);
//        return mailAddress.equals(linkLoggedInUser.getText());
//    }

    @Override
    public LoginPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
