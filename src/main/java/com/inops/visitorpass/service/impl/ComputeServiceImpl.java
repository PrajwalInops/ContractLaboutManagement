package com.inops.visitorpass.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.repository.ComputeRepository;
import com.inops.visitorpass.service.ICompute;

import lombok.RequiredArgsConstructor;

@Service("computeService")
@RequiredArgsConstructor
public class ComputeServiceImpl implements ICompute {

	private final ComputeRepository computeRepository;

	public void computeAll() {
		computeRepository.autometicComputeAll(new Date(), new Date());
	}

	@Override
	public void computeByDateAndEmployee(String employeeId, Date fromDate, Date toDate) {
		computeRepository.ComputeAttendanceFor(employeeId, fromDate, toDate, 0);
	}

	@Override
	public void computeAllByDate(Date fromDate, Date toDate) {
		computeRepository.autometicComputeAll(fromDate, toDate);
	}

	@Override
	public void createMusterByDateAndEmployee(String employeeId, Date fromDate) {
		computeRepository.CreateMusterFor(employeeId, fromDate, 0, 0);

	}

	@Override
	public void createMusterForAll(Date fromDate) {
		// TODO Auto-generated method stub

	}

}
