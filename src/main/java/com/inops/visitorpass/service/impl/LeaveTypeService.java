package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.repository.LeaveTypeRepository;
import com.inops.visitorpass.service.ILeaveType;

@Service("leaveTypeService")
public class LeaveTypeService implements ILeaveType {

	private LeaveTypeRepository leaveTypeRepository;

	public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
		this.leaveTypeRepository = leaveTypeRepository;
	}

	@Override
	public Optional<LeaveTypeEntity> findById(long id) {

		return leaveTypeRepository.findById(id);
	}

	@Override
	public Optional<List<LeaveTypeEntity>> findAll() {

		return Optional.of(leaveTypeRepository.findAll());
	}

	@Override
	public Optional<LeaveTypeEntity> create(LeaveTypeEntity leaveType) {

		return Optional.of(leaveTypeRepository.save(leaveType));
	}

	@Override
	public Optional<LeaveTypeEntity> update(LeaveTypeEntity leaveType) {
		return Optional.of(leaveTypeRepository.save(leaveType));
	}

	@Override
	public void delete(long leaveTypeId) {
		Optional<LeaveTypeEntity> leaveType = leaveTypeRepository.findById(leaveTypeId);
		leaveTypeRepository.delete(leaveType.get());	
	}

}
