package com.inops.visitorpass.whatsapp;

import com.inops.visitorpass.entity.ChannelApproval;
import com.inops.visitorpass.service.IChannelApprovalService;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class TwilioWebhookController {

	private final IChannelApprovalService channelApprovalService;

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String handleIncomingMessage(@RequestBody MultiValueMap<String, String> twilioRequest) {
		// Extract relevant information from the incoming request
		String fromNumber = twilioRequest.getFirst("From");
		String messageBody = twilioRequest.getFirst("Body");

		Optional<ChannelApproval> channelApproval = channelApprovalService.findByPhoneNo(fromNumber);

		if (channelApproval.isPresent()) {
			if (messageBody.equalsIgnoreCase("yes")) {
				// Respond to the incoming message
				// Body responseBody = new Body.Builder("Thanks for your message!").build();
				// Message responseMessage = new Message.Builder().body(responseBody).build();
				// MessagingResponse twiml = new
				// MessagingResponse.Builder().message(responseMessage).build();
				channelApprovalService.create(channelApproval.get().setApproved(true));
				return "Thanks for your approval!";
			} else if (messageBody.equalsIgnoreCase("no")) {
				return "Thank you for dis-approval!";
			}
		}
		// Process the incoming message (e.g., store in a database, respond, etc.)

		return "please send 'yes' for approval and 'no' for dis-approval ";
	}
}
