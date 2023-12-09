package com.inops.visitorpass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.CompensatoryOff;
import com.inops.visitorpass.entity.CompensatoryOffScheduler;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Holiday;
import com.inops.visitorpass.entity.LeaveBalance;
import com.inops.visitorpass.entity.LeaveTransactions;
import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.entity.RoleEntitlement;
import com.inops.visitorpass.entity.User;
import com.inops.visitorpass.service.ICompensatoryOff;
import com.inops.visitorpass.service.ICompensatoryOffScheduler;
import com.inops.visitorpass.service.IDivision;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IEntitlement;
import com.inops.visitorpass.service.IHoliday;
import com.inops.visitorpass.service.ILeaveBalance;
import com.inops.visitorpass.service.ILeaveTransactions;
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

	@Autowired
	ApplicationContext ctx;

	private final IEntitlement entitlementService;
	private final ILeaveType leaveTypeService;
	private final IHoliday holidayService;
	private final ICompensatoryOff compensatoryOffService;
	private final ICompensatoryOffScheduler compensatoryOffSchedulerService;
	private final ILeaveBalance leaveBalanceService;
	private final IEmployee employeeService;
	private final ILeaveTransactions leaveTransactionsService;
	private final IDivision division;

	private List<Employee> employees;
	private List<Division> divisions;
	private Division divisionObj;

	private LeaveTransactions selectedLeaveTransaction;
	private List<LeaveTransactions> selectedLeaveTransactions;
	private List<LeaveTransactions> leaveTransactions;

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
	private long leaveTypeId;

	private List<LeaveTypeEntity> leaveTypes;
	private LeaveTypeEntity leaveTypeObject;

	private List<Holiday> holidays;
	private List<CompensatoryOff> compensatoryOffs;
	private List<CompensatoryOffScheduler> compensatoryOffSchedulers;

	private List<RoleEntitlement> dbRoleEntitlements;

	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		leaveTypes = leaveTypeService.findAll().get();
		holidays = holidayService.findAll().get();
		compensatoryOffs = compensatoryOffService.findAll().get();
		compensatoryOffSchedulers = compensatoryOffSchedulerService.findAll().get();
		leaveBalances = leaveBalanceService.findAll().get();
		leaveTransactions = leaveTransactionsService.findAll().get();
		divisions = division.findAll().get();
		dbRoleEntitlements = entitlementService.findAll().get();
	}

	public void leaveDetails() {
		this.selectedLeaveTypeEntity = leaveTypes.stream().filter(leave -> leave.getLeaveTypeId() == leaveTypeId)
				.findAny().orElse(null);
	}

	public void saveLeave() {
		try {
			if (this.selectedLeaveTypeEntity.getLeaveTypeId() == 0l) {
				leaveTypeService.create(selectedLeaveTypeEntity);
				leaveTypes.add(selectedLeaveTypeEntity);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Leave " + selectedLeaveTypeEntity.getLeaveName() + " created successfully"));
			} else {
				leaveTypeService.create(selectedLeaveTypeEntity);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Leave " + selectedLeaveTypeEntity.getLeaveName() + " updated successfully"));
			}

			PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
			PrimeFaces.current().ajax().update("leaveNameAdvanced", "manage-product-content", ":form:messages",
					":form:dt-products");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}
	
	public void deleteLeave() {

		leaveTypeService.delete(selectedLeaveTypeEntity);
		this.leaveTypes.remove(this.selectedLeaveTypeEntity);
		if (this.selectedLeaveTypeEntitys != null) {
			this.selectedLeaveTypeEntitys.remove(this.selectedLeaveTypeEntity);
		}
		this.selectedLeaveTypeEntity = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "LeaveType deleted successfully");
	}

	public void deleteLeaves() {
		leaveTypeService.deleteAll(this.selectedLeaveTypeEntitys);
		this.leaveTypes.removeAll(this.selectedLeaveTypeEntitys);
		this.selectedLeaveTypeEntitys = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "LeaveTypes deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteLeaveButtonMessage() {
		if (hasSelectedLeavess()) {
			int size = this.selectedLeaveTypeEntitys.size();
			return size > 1 ? size + " leaves selected" : "1 leave selected";
		}

		return "Delete";
	}

	public boolean hasSelectedLeavess() {
		return this.selectedLeaveTypeEntitys != null && !this.selectedLeaveTypeEntitys.isEmpty();
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

	public void saveCompOffScheduler() {
		try {
			if (this.selectedCompensatoryOffScheduler.getId() == null) {
				compensatoryOffSchedulerService.save(selectedCompensatoryOffScheduler);
				this.compensatoryOffSchedulers.add(selectedCompensatoryOffScheduler);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "CompOff Scheduler Added successfully");

			} else {
				CompensatoryOffScheduler compOffScheduler = compensatoryOffSchedulers.stream()
						.filter(scheduler -> scheduler.getId() == selectedCompensatoryOffScheduler.getId()).findAny()
						.orElse(null);
				compOffScheduler.setDateBefore(selectedCompensatoryOffScheduler.getDateBefore());
				compOffScheduler.setIncludeOverTime(selectedCompensatoryOffScheduler.isIncludeOverTime());
				compOffScheduler.setName(selectedCompensatoryOffScheduler.getName());
				compOffScheduler.setTimeToSchedule(selectedCompensatoryOffScheduler.getTimeToSchedule());
				compOffScheduler.setDivision(selectedCompensatoryOffScheduler.getDivision());
				compensatoryOffSchedulerService.save(selectedCompensatoryOffScheduler);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "CompOff Scheduler updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteCompOffScheduler() {

		compensatoryOffSchedulerService.delete(selectedCompensatoryOffScheduler);
		this.compensatoryOffSchedulers.remove(this.selectedCompensatoryOffScheduler);
		if (this.selectedCompensatoryOffSchedulers != null) {
			this.selectedCompensatoryOffSchedulers.remove(this.selectedCompensatoryOffScheduler);
		}
		this.selectedCompensatoryOffScheduler = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "CompOff Scheduler deleted successfully");
	}

	public void deleteCompOffSchedulers() {
		compensatoryOffSchedulerService.deleteAll(this.selectedCompensatoryOffSchedulers);
		this.compensatoryOffSchedulers.removeAll(this.selectedCompensatoryOffSchedulers);
		this.selectedCompensatoryOffSchedulers = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "CompOff Scheduler deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteSchedulerButtonMessage() {
		if (hasSelectedSchedulers()) {
			int size = this.selectedCompensatoryOffSchedulers.size();
			return size > 1 ? size + " scheduler selected" : "1 scheduler selected";
		}

		return "Delete";
	}

	public boolean hasSelectedSchedulers() {
		return this.selectedCompensatoryOffSchedulers != null && !this.selectedCompensatoryOffSchedulers.isEmpty();
	}

	public void openNew() {
		this.selectedHoliday = new Holiday();
		this.selectedLeaveTransaction = new LeaveTransactions();
		this.selectedLeaveTypeEntity = new LeaveTypeEntity();
		this.selectedCompensatoryOff = new CompensatoryOff();
		this.selectedCompensatoryOffScheduler = new CompensatoryOffScheduler();
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
		// this.holidays.removeAll(this.selectedHolidays);
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
		// pass back to root
		PrimeFaces.current().dialog().closeDynamic(event.getObject());
	}

	public void openLevel1() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		PrimeFaces.current().dialog().openDynamic("level1", options, null);
	}

	public void onReturnFromLevel1(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data Returned", event.getObject().toString()));
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
