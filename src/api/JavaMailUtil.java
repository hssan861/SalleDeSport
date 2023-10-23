/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class JavaMailUtil {
    public static void sendMail(String recepient) throws MessagingException{
           System.out.println("Prepering to send email");

        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccoutEmail = "mohamedamine.mguidich@esprit.tn";
        String password="223JMT4823";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccoutEmail, password) ;
        }
        
    });
   Message message = prepareMessage(session ,myAccoutEmail, recepient) ;
   Transport.send(message);
   System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccoutEmail, String recepient ) {
        
        try { 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccoutEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("📢 New Activity Alert !! 📢");
            String htmlcode ="<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"    <title>New Activity Alert</title>\n" +
"</head>\n" +
"<body>\n" +
"    <div style=\"text-align: center; background-color: #87CEEB; padding: 20px;\">\n" +
"        <h1>New Activity Alert</h1>\n" +
"    </div>\n" +
"    <div style=\"margin: 20px;\">\n" +
"        <p>Dear Coach,</p>\n" +
"        <p>You're in for an exciting change! We've introduced a new activity at our gym.</p>\n" +
"        <p>Please check your app for all the details.</p>\n" +
"        <p>Stay tuned for this new adventure!</p>\n" +
"        <p>Best,<br>\n" +
"        <br>\n" +
"        <br>\n" +
"        FitFlex</p>\n" +
"    </div>\n" +
"</body>\n" +
"</html>";
            message.setContent(htmlcode,"text/html");
            //message.setText("Hey There, \n Look my email!");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

}
