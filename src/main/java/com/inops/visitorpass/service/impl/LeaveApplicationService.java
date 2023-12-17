package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LeaveApplication;
import com.inops.visitorpass.repository.LeaveApplicationRepository;
import com.inops.visitorpass.service.ILeaveApplication;

import lombok.RequiredArgsConstructor;

@Service("LeaveApplicationService")
@RequiredArgsConstructor
public class LeaveApplicationService implements ILeaveApplication {

	private final LeaveApplicationRepository leaveApplicationRepository;

	@Override
	public Optional<List<LeaveApplication>> findAll() {
		return Optional.of(leaveApplicationRepository.findAll());
	}

	@Override
	public LeaveApplication save(LeaveApplication leaveApplication) {
		return leaveApplicationRepository.save(leaveApplication);
	}

	@Override
	public void delete(LeaveApplication leaveApplication) {
		leaveApplicationRepository.delete(leaveApplication);

	}

	@Override
	public void deleteAll(List<LeaveApplication> leaveApplications) {
		leaveApplicationRepository.deleteAll(leaveApplications);
	}

}
