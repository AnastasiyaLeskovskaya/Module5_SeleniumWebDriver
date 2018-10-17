package selenium.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Mail {


    private String subject;
    private String addressee_mail_field;
    private String text_field;
    public Mail() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config/config.ini")));
        }catch (IOException e){
            System.out.println("Config file not found");
        }

        this.subject =  properties.getProperty("SUBJECT_FIELD");
        this.addressee_mail_field =  properties.getProperty("ADDRESSEE_MAIL_FIELD");
        this.text_field =  properties.getProperty("TEXT_FIELD");
    }

    public String getAddressee_mail_field() {
        return addressee_mail_field;
    }

    public void setAddressee_mail_field(String addressee_mail_field) {
        this.addressee_mail_field = addressee_mail_field;
    }

    public String getText_field() {
        return text_field;
    }

    public void setText_field(String text_field) {
        this.text_field = text_field;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "subject='" + subject + '\'' +
                ", addressee_mail_field='" + addressee_mail_field + '\'' +
                ", text_field='" + text_field + '\'' +
                '}';
    }
}
