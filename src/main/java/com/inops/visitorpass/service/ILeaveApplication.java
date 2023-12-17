package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.LeaveApplication;

public interface ILeaveApplication {

	Optional<List<LeaveApplication>> findAll();
	
	LeaveApplication save(LeaveApplication leaveApplication);

	void delete(LeaveApplication leaveApplication);

	void deleteAll(List<LeaveApplication> leaveApplications);
}
