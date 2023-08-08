package com.inops.visitorpass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.ChannelApproval;

public interface ChannelApprovalRepository extends JpaRepository<ChannelApproval, Long> {

	Optional<ChannelApproval> findByMailIdAndIsApproved(String mailId, boolean isApproved);

	Optional<ChannelApproval> findByPhoneNo(String phoneNo);

	Optional<ChannelApproval> findByPhoneNoAndIsApproved(String phoneNo, boolean isApproved);

}
