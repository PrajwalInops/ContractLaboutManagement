package com.inops.visitorpass.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.entity.TransactionId;
import com.inops.visitorpass.service.ICompute;
import com.inops.visitorpass.service.IDailyTransaction;
import com.inops.visitorpass.service.IMuster;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("transactionController")
@Scope("session")
@RequiredArgsConstructor
public class TransactionController {

	@Autowired
	ApplicationContext ctx;

	private final IMuster musterService;
	private final ICompute computeService;
	private final IDailyTransaction dailyTransactionService;

	ZoneId defaultZoneId = ZoneId.systemDefault();

	private List<Muster> musters;
	private Muster selectedMuster;
	private String employeeId;
	private Employee employee;
	private List<Employee> employees;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String title;
	private String type = "Single";
	private boolean enableAll;

	@PostConstruct
	public void init() {
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		LocalDate currentDate = LocalDate.now();
		musters = musterService.findByAttendanceDateBetween(currentDate.minusDays(2), currentDate).get();
	}

	public void openNew() {
		// this.employee = new Employee();
		this.selectedMuster = new Muster();
	}

	public void searchTransaction() {
		if (type.equals("All")) {
			addMessage(FacesMessage.SEVERITY_WARN, "Warn Message", "Please select one employee to view Transactions");
		} else {
			musters = musterService.findAllByAttendanceDateBetweenAndEmployeeId(fromDate, toDate, employeeId).get();
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Transaction view for: " + employeeId);
		}
	}

	public void compute() {
		if (type.equals("All")) {
			employees.forEach(employee -> {
				computeService.computeByDateAndEmployee(employee.getEmployeeId(),
						Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()),
						Date.from(toDate.atStartOfDay(defaultZoneId).toInstant()));
			});
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Transaction computed successfully for All employees");
		} else {
			computeService.computeByDateAndEmployee(employeeId,
					Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()),
					Date.from(toDate.atStartOfDay(defaultZoneId).toInstant()));

			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Transaction computed successfully for: " + employeeId);
		}
	}

	public void muster() {
		if (type.equals("All")) {
			employees.forEach(employee -> {
				computeService.createMusterByDateAndEmployee(employee.getEmployeeId(),
						Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()));
			});
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Muster created successfully for all employees ");

		} else {
			computeService.createMusterByDateAndEmployee(employeeId,
					Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()));
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Muster created successfully for: " + employeeId);
		}
	}

	public void addEvent() {
		if (employeeId == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"exception at the time of punch addition, Please selesct employeeId  ");
		} else {

			Date attendanceDate = Date.from(startTime.with(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());

			Transaction transaction = new Transaction(new TransactionId(employeeId, startTime, "I"), "I", "P", null,
					attendanceDate, 0, title, 0, "F", null, null);
			dailyTransactionService.save(transaction);

			if (endTime.isAfter(startTime)) {
				attendanceDate = Date.from(endTime.with(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());

				transaction.getTransactionId().setTransactionTime(endTime);
				transaction.getTransactionId().setInputOutputFlag("O");
				transaction.setAttendanceDate(attendanceDate);
				transaction.setActualIOFlag("O");
				dailyTransactionService.save(transaction);
			}

			addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Punch added successfully for: " + employeeId);
		}
	}

	public void onTypeChange() {
		if (type.equals("All")) {
			enableAll = true;
		} else {
			enableAll = false;
		}
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
