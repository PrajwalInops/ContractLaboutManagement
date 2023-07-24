package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.LeaveTypeEntity;

public interface ILeaveType {

	Optional<List<LeaveTypeEntity>> findAll();
	
	Optional<LeaveTypeEntity> findById(long id);
	
	Optional<LeaveTypeEntity> create(LeaveTypeEntity leaveType);
	
	Optional<LeaveTypeEntity> update(LeaveTypeEntity leaveType);
	
	void delete(long leaveTypeId);
	
}
