/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import pi_salle_de_sport.Entities.Reservation;

/**
 *
 * @author HP
 */
public class SendSMS {
    public static final String ACCOUNT_SID = "ACe5046d7c57cb2cdbbc2e06d8eceac9b6";
    public static final String AUTH_TOKEN = "18d2d0ca969c52db8eaa50aee6f49b60";
    //Your Twilio Password is : gnuFA^6L63DNs!j1234
    public static void sendSMS(Reservation P) {
        Twilio.init("ACe5046d7c57cb2cdbbc2e06d8eceac9b6", "18d2d0ca969c52db8eaa50aee6f49b60");

        String messageBody = "Votre Reservation est confirmé!! ";
       com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
    new PhoneNumber("+21625461635"),
    new PhoneNumber("+15139888121"),
    messageBody
       
).create();

        System.out.println(message.getSid());
    }
}
