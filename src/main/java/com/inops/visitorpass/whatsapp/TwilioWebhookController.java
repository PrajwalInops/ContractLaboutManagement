package com.inops.visitorpass.whatsapp;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inops.visitorpass.entity.ChannelApproval;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.IChannelApprovalService;
import com.inops.visitorpass.service.IVisitorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class TwilioWebhookController {

	private final IChannelApprovalService channelApprovalService;
	private final IVisitorService visitorService;

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String handleIncomingMessage(@RequestBody MultiValueMap<String, String> twilioRequest) {
		// Extract relevant information from the incoming request
		String fromNumber = twilioRequest.getFirst("From");
		String messageBody = twilioRequest.getFirst("Body");

		Optional<ChannelApproval> channelApproval = channelApprovalService
				.findByPhoneNoAndIsApproved(fromNumber.split(":")[1], false);

		if (channelApproval.isPresent()) {
			if (messageBody.equalsIgnoreCase("Yes")) {
				// Respond to the incoming message
				// Body responseBody = new Body.Builder("Thanks for your message!").build();
				// Message responseMessage = new Message.Builder().body(responseBody).build();
				// MessagingResponse twiml = new
				// MessagingResponse.Builder().message(responseMessage).build();
				channelApprovalService.create(channelApproval.get().setApproved(true));
				Visitor visitor = visitorService.findByMobileNo(channelApproval.get().getVisitorMobileNo()).get();
				visitor.setApproved(true);
				visitorService.save(visitor);
				return "Thanks for your approval!";
			} else if (messageBody.equalsIgnoreCase("No")) {
				channelApprovalService.create(channelApproval.get().setApproved(true));
				return "Thank you for dis-approval!";
			}
		}
		else
		{
			return "No visitors for your approval thank you!";
		}
		// Process the incoming message (e.g., store in a database, respond, etc.)

		return "please send 'yes' for approval and 'no' for dis-approval";
	}
}
