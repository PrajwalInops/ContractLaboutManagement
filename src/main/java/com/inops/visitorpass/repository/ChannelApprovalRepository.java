package com.inops.visitorpass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.ChannelApproval;

public interface ChannelApprovalRepository  extends JpaRepository<ChannelApproval, Long>{
	
	 Optional<ChannelApproval> findByMailId(String mailId);
	
	 Optional<ChannelApproval> findByPhoneNo(String phoneNo);

}
