package com.inops.visitorpass.controller;

import java.time.LocalDate;
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
import com.inops.visitorpass.service.ICompute;
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

	ZoneId defaultZoneId = ZoneId.systemDefault();

	private List<Muster> musters;
	private Muster selectedMuster;
	private String employeeId;
	private Employee employee;
	private List<Employee> employees;
	private LocalDate fromDate;
	private LocalDate toDate;

	@PostConstruct
	public void init() {
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		LocalDate currentDate = LocalDate.now();
		musters = musterService.findByAttendanceDateBetween(currentDate.minusDays(2), currentDate).get();
	}

	public void openNew() {
		this.employee = new Employee();
	}

	public void searchTransaction() {
		musters = musterService.findAllByAttendanceDateBetweenAndEmployeeId(fromDate, toDate, employeeId).get();
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Transaction view for: " + employeeId);
	}

	public void compute() {
		computeService.computeByDateAndEmployee(employeeId, Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()),
				Date.from(toDate.atStartOfDay(defaultZoneId).toInstant()));
		
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Transaction computed successfully for: " + employeeId);
	}

	public void muster() {
		computeService.createMusterByDateAndEmployee(employeeId,
				Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant()));
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Muster created successfully for: " + employeeId);
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}


}
