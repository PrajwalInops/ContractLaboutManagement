package com.inops.visitorpass.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.LeaveEncashment;

public interface ILeaveEncashment {

	Optional<List<LeaveEncashment>> findAllByFromDateAndToDateAndEmployeeIdIn(LocalDate start, LocalDate end,
			List<String> employeeId);
}
