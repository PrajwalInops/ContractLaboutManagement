package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.ChannelApproval;
import com.inops.visitorpass.repository.ChannelApprovalRepository;
import com.inops.visitorpass.service.IChannelApprovalService;

import lombok.RequiredArgsConstructor;

@Service("channelApprovalService")
@RequiredArgsConstructor
public class ChannelApprovalServiceImpl implements IChannelApprovalService {

	private final ChannelApprovalRepository approvalRepository;

	@Override
	public Optional<List<ChannelApproval>> findAll() {
		return Optional.of(approvalRepository.findAll());
	}

	@Override
	public Optional<ChannelApproval> findByPhoneNo(String phoneNo) {
		return approvalRepository.findByPhoneNo(phoneNo);
	}

	@Override
	public Optional<ChannelApproval> findByMailId(String mailId) {
		return approvalRepository.findByPhoneNo(mailId);
	}

	@Override
	public ChannelApproval create(ChannelApproval channelApproval) {
		return approvalRepository.save(channelApproval);
	}

	@Override
	public Optional<ChannelApproval> findByPhoneNoAndIsApproved(String phoneNo, boolean isApproved) {
		return approvalRepository.findByPhoneNoAndIsApproved(phoneNo, false);
	}

}
