package selenium.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
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
        Date date = new Date();
        this.firstname = date.toString();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstname, contact.firstname) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(companyName, contact.companyName) &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastName, companyName, email);
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
