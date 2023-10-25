/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author USER
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;
public class MailUtil{
    
 public static boolean sendPasswordResetMail(String toEmail,String verificationCode){
        final String fromEmail = "hssan.ghorbel@esprit.tn";//user.getFromEmail(); //requires valid gmail id
        final String password = "223JFT4890";//user.getPassword(); // correct password for gmail id

        Properties props = new Properties();
         props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
         //enable authentication
               try
        {
                Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
                        @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
                Session session = Session.getDefaultInstance(props,null);
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");

          msg.setFrom(new InternetAddress(fromEmail, "CHANGE THIS"));

          msg.setReplyTo(InternetAddress.parse(fromEmail, false));

          msg.setSubject("app test email", "UTF-8");

          msg.setText("//TODO:CHANGE THIS \n"+verificationCode, "UTF-8");

          msg.setSentDate(new Date());

          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          System.out.println("Message is ready");
              Transport trans=session.getTransport("smtp");
              trans.connect("smtp.gmail.com",fromEmail,password);
              trans.sendMessage(msg,msg.getAllRecipients());
          return true;          
          //System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
          return false;
        }
    }
}