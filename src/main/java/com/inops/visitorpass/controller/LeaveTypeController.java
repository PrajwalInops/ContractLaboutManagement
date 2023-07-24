package com.inops.visitorpass.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.service.ILeaveType;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("leaveTypeController")
@Scope("session")
public class LeaveTypeController {

	private ILeaveType leaveTypeService;

	private String leaveName;
	private String leaveCode;
	private String leaveType;
	private String leaveUnits;
	private String leaveBalanceBasedOn;
	private Date validFrom;
	private Date validTo;
	private String leaveDescription;

	private String applicableFor;
	private String applicableRole;
	private String applicableLocation;
	private String applicableGender;
	private String applicableMaritalStatus;

	private boolean quarterDay;
	private boolean halfDay;
	private boolean beyondPermittedLeave;
	private boolean roundOffPermittedLeave;
	private boolean excludeHolidays;
	private boolean excludeWeekEndsDays;
	private boolean includeAllHolidaysAndWeeklyOffs;
	private float maxConsecutiveDays;
	private int holidayWeekendConsicutive;
	private int applicationSubmitBefore;

	private int effectiveAfter;
	private String entitlementInterval;
	private String effectiveFrom;

	private boolean accrual;
	private String accrualType;
	private String accrualOn;
	private String accrualMonth;
	private float noOfDays;

	private boolean reset;
	private String resetType;
	private String resetOn;
	private String resetMonth;

	private String carryForwardType;
	private int unit;
	private String carryForwardScale;
	private int carryForwardMaxLimit;
	private int expiresIn;
	private String carryForwardInterval;

	private int encashment;
	private String encashmentScale;
	private int encashmentMaxLimit;
	private String prorateAccrual;
	
	private List<LeaveTypeEntity> leaveTypes;
	private LeaveTypeEntity leaveTypeObject;

	public LeaveTypeController(ILeaveType leaveTypeService) {
		super();
		this.leaveTypeService = leaveTypeService;
	}

	
	@PostConstruct
	public void init()  {
		leaveTypes = leaveTypeService.findAll().get();
	}
	
	public void leaveDetails()
	{
		leaveCode = leaveTypeObject.getLeaveCode();
	}

	public void save() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + leaveName + " " + leaveCode));
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
}
