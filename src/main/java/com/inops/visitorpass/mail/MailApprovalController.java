package com.inops.visitorpass.mail;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inops.visitorpass.entity.ChannelApproval;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.IChannelApprovalService;
import com.inops.visitorpass.service.IVisitorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailApprovalController {

	private final IChannelApprovalService channelApprovalService;
	private final IVisitorService visitorService;

	@GetMapping("/approvevisitor")
	public String approveVisitor(@RequestParam(name = "from") String from,
			@RequestParam(name = "isApproved") boolean isApproval) {

		Optional<ChannelApproval> channelApproval = channelApprovalService.findByMailIdAndIsApproved(from, false);

		if (isApproval) {
			channelApprovalService.create(channelApproval.get().setApproved(true));
			Visitor visitor = visitorService.findByMobileNo(channelApproval.get().getVisitorMobileNo()).get();
			visitor.setApproved(true);
			visitorService.save(visitor);
			return "Thanks for your approval, Thank you!";
		} else if (!isApproval) {
			channelApprovalService.create(channelApproval.get().setApproved(true));
		}
		return "Approval has not accepted, Thank you!";

	}

}
