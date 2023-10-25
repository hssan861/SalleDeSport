/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import models.Reservation;

/**
 *
 * @author HP
 */
public class SendSM {
    public static final String ACCOUNT_SID = "ACe5046d7c57cb2cdbbc2e06d8eceac9b6";
    public static final String AUTH_TOKEN = "068d587def2eeb7663ad7cc4ff1e5859";
    //Your Twilio Password is : gnuFA^6L63DNs!j1234
    public static void sendSM(Reservation P) {
        Twilio.init("ACe5046d7c57cb2cdbbc2e06d8eceac9b6", "068d587def2eeb7663ad7cc4ff1e5859");

        String messageBody = "Votre Reservation est confirmé!! ";
       com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
    new PhoneNumber("+21625461635"),
    new PhoneNumber("+15139888121"),
    messageBody
       
).create();

        System.out.println(message.getSid());
    }
}
