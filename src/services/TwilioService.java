/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author rayen
 */
public class TwilioService {
    private static final String ACCOUNT_SID = "AC0f26a87bc35e437038bda8d0bc069986";
    private static final String AUTH_TOKEN = "70c90468440f0132efb07145d8e8fe19";
    private static final String FROM_NUMBER = "+15746525693";

    public static void sendSms(String toNumber, String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(FROM_NUMBER),
                        messageText)
                .create();

        System.out.println("Message SID: " + message.getSid());
    }
    
}
