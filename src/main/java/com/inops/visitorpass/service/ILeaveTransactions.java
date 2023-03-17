package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.LeaveTransactions;

public interface ILeaveTransactions {

	Optional<List<LeaveTransactions>> findAll();

	Optional<List<LeaveTransactions>> findAllByFromDateBetweenAndEmployee(LocalDate start, LocalDate end,
			List<String> employeeId);
}
