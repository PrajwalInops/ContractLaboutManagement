package com.inops.visitorpass.whatsapp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final TwilioService twilioService;

    public MessageController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send-whatsapp")
    public void sendWhatsAppMessage(@RequestBody MessageDto messageDto) {
        twilioService.sendWhatsAppMessage(messageDto.getToNumber(), messageDto.getMessage());
    }
}
