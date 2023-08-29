package com.inops.visitorpass.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.CompensatoryOffScheduler;
import com.inops.visitorpass.entity.Holiday;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.service.ICompensatoryOff;
import com.inops.visitorpass.service.ICompensatoryOffScheduler;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IHoliday;
import com.inops.visitorpass.service.ILeaveBalance;
import com.inops.visitorpass.service.ILeaveType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("leaveTypeController")
@Scope("session")
@RequiredArgsConstructor
public class LeaveTypeController {

	private final ILeaveType leaveTypeService;
	private final IHoliday holidayService;
	private final ICompensatoryOff compensatoryOffService;
	private final ICompensatoryOffScheduler compensatoryOffSchedulerService;
	private final ILeaveBalance leaveBalanceService;
	private final IEmployee employeeService;

	private LeaveBalance selectedLeaveBalance;
	private List<LeaveBalance> selectedLeaveBalances;
	private List<LeaveBalance> leaveBalances;
	
	private LeaveTypeEntity leaveTypeEntity;
	private LeaveTypeEntity selectedLeaveTypeEntity;
	private List<LeaveTypeEntity> selectedLeaveTypeEntitys;
	
	private CompensatoryOff selectedCompensatoryOff;
	private List<CompensatoryOff> selectedCompensatoryOffs;

	private CompensatoryOffScheduler selectedCompensatoryOffScheduler;
	private List<CompensatoryOffScheduler> selectedCompensatoryOffSchedulers;

	private Holiday selectedHoliday;
	private List<Holiday> selectedHolidays;

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

	private List<Holiday> holidays;
	private List<CompensatoryOff> compensatoryOffs;
	private List<CompensatoryOffScheduler> compensatoryOffSchedulers;

	@PostConstruct
	public void init() {
		leaveTypes = leaveTypeService.findAll().get();
		holidays = holidayService.findAll().get();
		compensatoryOffs = compensatoryOffService.findAll().get();
		compensatoryOffSchedulers = compensatoryOffSchedulerService.findAll().get();
		leaveBalances = leaveBalanceService.findAll().get();
	}

	public void leaveDetails() {
		leaveCode = leaveTypeObject.getLeaveCode();
	}

	public void save() {
		LeaveTypeEntity leaveTypeObj = new LeaveTypeEntity(0L, leaveName, leaveCode, leaveType, leaveUnits,
				leaveBalanceBasedOn, validFrom, validTo, leaveDescription, applicableFor, applicableRole,
				applicableLocation, applicableGender, applicableMaritalStatus, quarterDay, halfDay,
				beyondPermittedLeave, roundOffPermittedLeave, excludeHolidays, excludeWeekEndsDays,
				includeAllHolidaysAndWeeklyOffs, maxConsecutiveDays, holidayWeekendConsicutive, applicationSubmitBefore,
				effectiveAfter, entitlementInterval, effectiveFrom, accrual, accrualType, accrualOn, accrualMonth,
				noOfDays, reset, resetType, resetOn, resetMonth, carryForwardType, unit, carryForwardScale,
				carryForwardMaxLimit, expiresIn, carryForwardInterval, encashment, encashmentScale, encashmentMaxLimit,
				prorateAccrual);
		leaveTypeService.create(leaveTypeObj);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Leave " + leaveName + " " + leaveCode + " created successfully"));
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveHoliday() {

	}

	public void deleteHoliday(Holiday holiday) {
	}

	public void saveCompOff() {
	}

	public void deleteCompOff(CompensatoryOff compOff) {
	}

	public void openNew() {
		this.selectedHoliday = new Holiday();
	}

	public void saveProduct() {
		if (this.selectedHoliday.getId() == null) {
			// this.selectedHoliday.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			// this.products.add(this.selectedProduct);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
		}

		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}

	public void deleteProduct() {
		this.holidays.remove(this.selectedHoliday);
		this.selectedHolidays.remove(this.selectedHoliday);
		this.selectedHoliday = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedProducts()) {
			int size = this.selectedHolidays.size();
			return size > 1 ? size + " products selected" : "1 product selected";
		}

		return "Delete";
	}

	public boolean hasSelectedProducts() {
		return this.selectedHolidays != null && !this.selectedHolidays.isEmpty();
	}

	public void deleteSelectedProducts() {
		// this.holidays.removeAll(this.selectedHoliday);
		this.selectedHolidays = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}
	
	public void openLevel2() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        PrimeFaces.current().dialog().openDynamic("level2", options, null);
    }

    public void onReturnFromLevel2(SelectEvent event) {
        //pass back to root
        PrimeFaces.current().dialog().closeDynamic(event.getObject());
    }
    
    public void openLevel1() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        PrimeFaces.current().dialog().openDynamic("level1", options, null);
    }

    public void onReturnFromLevel1(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
    }

}
