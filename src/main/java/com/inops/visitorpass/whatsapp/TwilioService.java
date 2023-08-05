package com.inops.visitorpass.whatsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.from.number}")
    private String fromNumber;

    public void sendWhatsAppMessage(String toNumber, String messageBody) {
        // Initialize Twilio with your Account SID and Auth Token
        Twilio.init(accountSid, authToken);

        // Sending a WhatsApp message
        Message message = Message.creator(
            new PhoneNumber("whatsapp:" + toNumber), // Receiver's WhatsApp number
            new PhoneNumber("whatsapp:" + fromNumber), // Your Twilio WhatsApp number
            messageBody
        ).create();

        System.out.println("Message SID: " + message.toString());
    }
}
