package unswbook;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private String to;
    private String from = "comp9321youneversleepwell@gmail.com";
    private String host = "localhost";

    // Get system properties object
    private Properties props = System.getProperties();

    public SendEmail(String to){
        this.to = to;
    }

    public void sendConfirmationEmail(String code){
        // Setup mail server
        //properties.setProperty("mail.smtp.host", host);
        // Get session
        //properties.put("mail.smtp.auth", "true"); 


        /* Get the default Session object.
        Session session = Session.getDefaultInstance(properties);*/

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String password = "youneversleepwell";
        Session session = Session.getDefaultInstance(props,
                new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }});
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Activate your account");

            // Now set the actual message
            message.setContent("<h1>Hi! Welcome to UNSWBook! Please click the link below to activate your account." +
            "</h1><h3><a href='http://localhost:8080/unswbook/login_register?action=activateAccount&&code="+code+"'>http://localhost:8080/activate_account</a></h3>","text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
