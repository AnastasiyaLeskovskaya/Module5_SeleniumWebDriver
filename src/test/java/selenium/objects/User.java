package selenium.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class User {
    private String login;
    private String password;
    private String mailAddress;

    public User() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config/config.ini")));
        }catch (IOException e){
            System.out.println("Config file not found");
        }
        this.mailAddress = properties.getProperty("MAIL_ADDRESS");
        this.login = properties.getProperty("LOGIN");
        this.password = properties.getProperty("PASSWORD");
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(mailAddress, user.mailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, mailAddress);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
