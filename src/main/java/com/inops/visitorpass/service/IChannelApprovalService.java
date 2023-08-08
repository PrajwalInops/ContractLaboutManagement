package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.ChannelApproval;

public interface IChannelApprovalService {

	Optional<List<ChannelApproval>> findAll();

	Optional<ChannelApproval> findByPhoneNo(String phoneNo);

	Optional<ChannelApproval> findByPhoneNoAndIsApproved(String phoneNo, boolean isApproved);

	Optional<ChannelApproval> findByMailIdAndIsApproved(String mailId, boolean isApproved);

	ChannelApproval create(ChannelApproval channelApproval);
}
