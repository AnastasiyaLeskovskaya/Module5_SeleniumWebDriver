package selenium.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Contact {
    private String firstname;
    private String lastName;
    private String companyName;
    private String email;

    public Contact() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config/config.ini")));
        }catch (IOException e){
            System.out.println("Config file not found");
        }

        this.firstname = properties.getProperty("FIRST_NAME");
        this.lastName = properties.getProperty("LOGIN").substring(0, 11);
        this.companyName = properties.getProperty("SUBJECT_FIELD");
        this.email =  properties.getProperty("ADDRESSEE_MAIL_FIELD");
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
