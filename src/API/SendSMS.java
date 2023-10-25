/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import models.Participation;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import models.Event;

/**
 *
 * @author rayen
 */
public class SendSMS {
    public static final String ACCOUNT_SID = "AC9fbdaf2a96674c00d4700cc8b8b1f50a";
    public static final String AUTH_TOKEN = "b626f952f501b8beb617f8a37a43c1d0";
    //Your Twilio Password is : gnuFA^6L63DNs!j1234
    public static void sendSMS(Participation P) {
        Twilio.init("AC9fbdaf2a96674c00d4700cc8b8b1f50a", "b626f952f501b8beb617f8a37a43c1d0");

        String messageBody = "Votre Reservation est confirmé : ";
       com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
    new PhoneNumber("+21693008976"),
    new PhoneNumber("+16063904993"),
    messageBody
       
).create();

        System.out.println(message.getSid());
    }
    
}
