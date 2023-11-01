package com.inops.visitorpass.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Muster;
import com.inops.visitorpass.entity.Transaction;
import com.inops.visitorpass.service.IDailyTransaction;
import com.inops.visitorpass.service.IMuster;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("punchController")
@Scope("session")
@RequiredArgsConstructor
public class PunchController {

	@Autowired
	ApplicationContext ctx;
	private final IDailyTransaction dailyTransactionService;
	private final IMuster musterService;

	private List<Transaction> transactions;

	private Transaction transaction;
	private String employeeId;
	private LocalDate startDate;
	private LocalDate endDate;

	private List<Employee> employees;

	private ScheduleModel eventModel;

	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();

	private boolean slotEventOverlap = true;
	private boolean showWeekNumbers = false;
	private boolean showHeader = true;
	private boolean draggable = true;
	private boolean resizable = true;
	private boolean selectable = false;
	private boolean showWeekends = true;
	private boolean tooltip = true;
	private boolean allDaySlot = true;
	private boolean rtl = false;

	ZoneId defaultZoneId = ZoneId.systemDefault();
	private double aspectRatio = Double.MIN_VALUE;
	private String serverTimeZone = ZoneId.systemDefault().toString();

	@PostConstruct
	public void init() {
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		List<Employee> dateOfBirths = employees.stream()
				.filter(emp -> (emp.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth())
						&& (emp.getDateOfBirth().getMonthValue() == LocalDate.now().getMonthValue()))
				.collect(Collectors.toList());

		eventModel = new DefaultScheduleModel();

		DefaultScheduleEvent<?> scheduleEventAllDay = DefaultScheduleEvent.builder()
				.title("Birthday's (AllDay) "
						+ dateOfBirths.stream().map(Employee::getEmployeeName).collect(Collectors.toList()))
				.startDate(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))
				.endDate(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)).description("")
				.backgroundColor("yellow")
				.borderColor("#27AE60").allDay(true).build();
		eventModel.addEvent(scheduleEventAllDay);

	}

	public void openNew() {
		this.transaction = new Transaction();
	}

	public void searchPunchs() {
		eventModel = new DefaultScheduleModel();
		List<Object[]> transactions = dailyTransactionService
				.findMinMaxPunchedTimeByDateRange(employeeId, startDate, endDate).get();
		List<Muster> musters = musterService.findAllByAttendanceDateBetweenAndEmployeeId(startDate, endDate, employeeId)
				.get();
		transactions.stream().forEach(trans -> {
			Muster muster = musters.stream().filter(must -> must.getMusterId().getAttendanceDate().equals(trans[0]))
					.findAny().orElse(null);
			if (muster != null) {
				float hours = (muster.getHoursWorked() / 60);
				String colour = hours >= 8 ? "green" : hours >= 4 && hours <= 6 ? "orange" : "red";
				DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
						.title("Attendance " + muster.getAttendanceId() + "& Hours Worked " + hours)
						.startDate((LocalDateTime) trans[1]).endDate((LocalDateTime) trans[2])
						.description("Employee " + employeeId + " Hours Worked " + muster.getHoursWorked())
						.borderColor(colour).build();
				eventModel.addEvent(event);
			}
		});

	}

	public void addEvent() {
		if (event.isAllDay()) {
			// see https://github.com/primefaces/primefaces/issues/1164
			if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
				event.setEndDate(event.getEndDate().plusDays(1));
			}
		}

		if (event.getId() == null) {
			eventModel.addEvent(event);
		} else {
			eventModel.updateEvent(event);
		}

		event = new DefaultScheduleEvent<>();
	}

	public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
		event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject())
				.endDate(selectEvent.getObject().plusHours(1)).build();
	}

	public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
		event = selectEvent.getObject();
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Delta:" + event.getDeltaAsDuration());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
