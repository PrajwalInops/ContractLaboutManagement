package com.inops.visitorpass.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.constant.InopsConstant;
import com.inops.visitorpass.domain.Kvp;
import com.inops.visitorpass.domain.Report;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.service.IDepartment;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.impl.ReportGenerationService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("reportController")
@Scope("session")
public class ReportController implements Serializable {

	private boolean skip;
	private List<SelectItem> reportTypes;
	private final IEmployee employeeService;
	private final IDepartment departmentService;
	private final ReportGenerationService reportGenerationService;

	@Autowired
	ApplicationContext ctx;

	public ReportController(IEmployee employeeService, IDepartment departmentService,
			ReportGenerationService reportGenerationService) {
		super();

		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.reportGenerationService = reportGenerationService;

	}

	Report report = new Report();

	private DualListModel<Kvp> pickSelectedTypes;
	private String type;
	private String reportName;
	private String reportType;
	private String selectionType;
	private StreamedContent file;
	private List<LocalDate> dateRange;
	private Optional<List<Employee>> getEmployees;

	@PostConstruct
	public void init() {
		reportTypes = new ArrayList<>();

		SelectItemGroup attendanceReports = new SelectItemGroup("Attendance Reports");
		attendanceReports.setSelectItems(new SelectItem[] {
				new SelectItem("Attendance Register", "Attendance Register"),
				new SelectItem("Late In Register", "Late In Register"),
				new SelectItem("Early Out Register", "Early Out Register"),
				new SelectItem("Extra Hours Register", "Extra Hours Register"),
				new SelectItem("Continous Absenteesim", "Continous Absenteesim"),
				new SelectItem("All Punches", "All Punches"), new SelectItem("Daily Summary", "Daily Summary") });

		SelectItemGroup leaveReports = new SelectItemGroup("Leave Reports");
		leaveReports.setSelectItems(new SelectItem[] { new SelectItem("United States", "United States"),
				new SelectItem("Brazil", "Brazil"), new SelectItem("Mexico", "Mexico") });

		SelectItemGroup visitorReports = new SelectItemGroup("Visitors Reports");
		visitorReports.setSelectItems(new SelectItem[] { new SelectItem("Visitors Register", "Visitors Register"), });

		//reportTypes.add(attendanceReports);
		//reportTypes.add(leaveReports);
		reportTypes.add(visitorReports);

		List<Kvp> pickSource = new ArrayList<>();
		List<Kvp> pickTarget = new ArrayList<>();

		pickSelectedTypes = new DualListModel<>(pickSource, pickTarget);

		getEmployees = (Optional<List<Employee>>) ctx.getBean("getEmployees");
		fileDownload(null, "Reports");

	}

	public void setComp(AjaxBehaviorEvent event) {
		List<Kvp> pickSource = new ArrayList<>();
		List<Kvp> pickTarget = new ArrayList<>();

		if (report.getSelectionType().equals("Departments")) {
			pickSource = departmentService.findAll().get().stream().map(dept -> {
				return new Kvp(dept.getId(), dept.getDepartmentName());
			}).collect(Collectors.toList());
		} else if (report.getSelectionType().equals("Employees")) {
			pickSource = employeeService.findAll().get().stream().map(dept -> {
				return new Kvp(dept.getEmployeeId(), dept.getEmployeeName());
			}).collect(Collectors.toList());
		}

		pickSelectedTypes = new DualListModel<>(pickSource, pickTarget);

	}

	public void onTransfer(TransferEvent event) {
		StringBuilder builder = new StringBuilder();
		for (Object item : event.getItems()) {
			builder.append(((Kvp) item).getValue()).append("<br />");
		}

		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Items Transferred");
		msg.setDetail(builder.toString());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onSelect(SelectEvent<Kvp> event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().getValue()));
	}

	public void generate() throws IOException {
		List<Employee> filteredList = null;
		if (report.getSelectionType() != null) {
			if (report.getSelectionType().equals("Departments")) {

				filteredList = getEmployees.get().stream()
						.filter(empl -> pickSelectedTypes.getTarget().stream().anyMatch(dept ->

						empl.getDepartment().getId().equals(dept.getKey()))).collect(Collectors.toList());
			} else if (report.getSelectionType().equals("Employees")) {

				filteredList = getEmployees.get().stream()
						.filter(empl -> pickSelectedTypes.getTarget().stream().anyMatch(employee ->

						employee.getKey().equals(empl.getEmployeeId()))).collect(Collectors.toList());

			} else if (report.getSelectionType().equals("Caders")) {

			}
		}
		byte[] buffer = null;
		switch (report.getReportName()) {
		case InopsConstant.ATTENDANCE_REGISTER:
		case InopsConstant.LATEIN_REGISTER:
		case InopsConstant.EARLYOUT_REGISTER:
		case InopsConstant.EXTRAHOURS_REGISTER:
			buffer = reportGenerationService.getRegistery().generate(report.getDateRange().get(0),
					report.getDateRange().get(1), filteredList, report.getReportName());
			break;
		case "Continous Absenteesim":
			buffer = reportGenerationService.getContinousAbsenteesim().generate(report.getDateRange().get(0),
					report.getDateRange().get(1), filteredList, report.getReportName());
			break;

		case "All Punches":
			buffer = reportGenerationService.getAllPunches().generate(report.getDateRange().get(0),
					report.getDateRange().get(1), filteredList, report.getReportName());
			break;

		case "Daily Summary":
			buffer = reportGenerationService.getDailySummary().generate(report.getDateRange().get(0),
					report.getDateRange().get(1), filteredList, report.getReportName());
			break;

		case "Visitors Register":
			buffer = reportGenerationService.getDailyVisitors().generate(report.getDateRange().get(0),
					report.getDateRange().get(1), filteredList, report.getReportName());
			break;

		default:
			break;
		}
		if (buffer != null) {
			fileDownload(buffer, report.getReportName());
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Report Generated successfully : " + report.getReportName());
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"Report Generation failed for : " + report.getReportName());
		}
	}

	public void fileDownload(byte[] buffer, String fileName) {
		file = DefaultStreamedContent.builder().name(fileName + ".pdf").contentType("application/pdf")
				.stream(() -> new ByteArrayInputStream(buffer))
				// .stream(() ->
				// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Users\\User\\Prajwal\\source
				// code\\Visitorpass\\src\\main\\webapp\\resources\\demo\\media\\2515655.pdf"))
				.build();
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		final String currentStepId = event.getOldStep();
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}

		/*
		 * String stepToGo = event.getNewStep(); if
		 * (currentStepId.equalsIgnoreCase("...")) //last step {
		 * 
		 * stepToGo = "s10"; } return stepToGo;
		 */

	}

}
