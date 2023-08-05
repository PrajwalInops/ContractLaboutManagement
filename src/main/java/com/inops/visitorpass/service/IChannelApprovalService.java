package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.ChannelApproval;

public interface IChannelApprovalService {

	Optional<List<ChannelApproval>> findAll();
	
	Optional<ChannelApproval> findByPhoneNo(String phoneNo);
	
	Optional<ChannelApproval> findByMailId(String mailId);
	
	ChannelApproval create(ChannelApproval channelApproval);
}
