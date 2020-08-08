package auto.menu.generator.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private static final String FROM = "xxxxxx@gmail.com";
    private static final String PASSWORD = "xxxxxxx";
    private static final String TO = "xxxxxx@gmail.com";
    private final Session session;

    public SendEmail() {

        // Assuming you are sending email FROM through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        this.session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(FROM, PASSWORD);

            }

        });

        // Used TO debug SMTP issues
        session.setDebug(true);
    }

    public void send(String subject, String content){
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(FROM));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setContent(content, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
