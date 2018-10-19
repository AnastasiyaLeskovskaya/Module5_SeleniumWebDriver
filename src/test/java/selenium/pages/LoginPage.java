package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.User;

public class LoginPage extends AbstractPage {

    private final String BASE_URL = "https://mail.ru";

    @FindBy(id = "mailbox:login")
    private WebElement inputLogin;

    @FindBy(id = "mailbox:password")
    private WebElement inputPassword;

    @FindBy(css = "input.o-control")
    private WebElement buttonSubmit;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public HomePage login(User user)  {
        inputLogin.clear();
        inputLogin.sendKeys(user.getLogin());
        inputPassword.clear();
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return (new HomePage(driver));
    }

    @Override
    public LoginPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
