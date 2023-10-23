/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javax.mail.MessagingException;

/**
 *
 * @author HP
 */
public class JavaMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        // TODO code application logic here
        JavaMailUtil.sendMail("mguidich46@gmail.com");
    }
    
}
