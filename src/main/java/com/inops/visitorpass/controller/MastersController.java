package com.inops.visitorpass.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.entity.Designation;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Shift;
import com.inops.visitorpass.service.ICadre;
import com.inops.visitorpass.service.IDepartment;
import com.inops.visitorpass.service.IDesignation;
import com.inops.visitorpass.service.IDivision;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IShift;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("mastersController")
@Scope("session")
@RequiredArgsConstructor
public class MastersController {

	private final IDivision division;
	private final IDepartment departmentService;
	private final IDesignation designationService;
	private final ICadre cadreService;
	private final IShift shiftService;
	private final IEmployee employeeService;

	private Division selectedDivision;
	private List<Division> selectedDivisions;
	private List<Division> divisions;

	private Department selectedDepartment;
	private List<Department> selectedDepartments;
	private List<Department> departments;

	private Designation selectedDesignation;
	private List<Designation> selectedDesignations;
	private List<Designation> designations;

	private Cadre selectedCadre;
	private List<Cadre> selectedCadres;
	private List<Cadre> cadres;

	private Shift selectedShift;
	private List<Shift> selectedShifts;
	private List<Shift> shifts;
	private List<String> objShifts;

	private Employee selectedEmployee;
	private List<Employee> selectedEmployees;
	private List<Employee> employees;

	@PostConstruct
	public void init() {

		divisions = division.findAll().get();
		departments = departmentService.findAll().get();
		designations = designationService.findAll().get();
		cadres = cadreService.findAll().get();
		shifts = shiftService.findAll().get();
		employees = employeeService.findAll().get();
	}

	public void openNew() {
		this.selectedDivision = new Division();
		this.selectedDepartment = new Department();
		this.selectedDesignation = new Designation();
		this.selectedCadre = new Cadre();
		this.selectedShift = new Shift();
		this.selectedEmployee = new Employee();
	}

	public void saveDivision() {
		try {
			if (this.selectedDivision.getDivisionId() == 0L) {
				division.save(selectedDivision);
				this.divisions.add(selectedDivision);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Division Added successfully");

			} else {
				Division division = divisions.stream()
						.filter(div -> div.getDivisionId() == selectedDivision.getDivisionId()).findAny().orElse(null);
				division.setDivisionName(selectedDivision.getDivisionName());
				division.setCards(null);

				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Division updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteDivision() {

		division.delete(selectedDivision);
		this.divisions.remove(this.selectedDivision);
		if (this.selectedDivisions != null) {
			this.selectedDivisions.remove(this.selectedDivision);
		}
		this.selectedDivision = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Division deleted successfully");
	}

	public void deleteDivisions() {
		division.deleteAll(this.selectedDivisions);
		this.divisions.removeAll(this.selectedDivisions);
		this.selectedDivisions = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Divisions deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteDivisionsButtonMessage() {
		if (hasSelectedDivisions()) {
			int size = this.selectedDivisions.size();
			return size > 1 ? size + " divisions selected" : "1 divisions selected";
		}

		return "Delete";
	}

	public boolean hasSelectedDivisions() {
		return this.selectedDivisions != null && !this.selectedDivisions.isEmpty();
	}

	public void saveDepartment() {
		try {
			if (this.selectedDepartment.getId() == null) {
				departmentService.save(selectedDepartment);
				this.departments.add(selectedDepartment);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Department Added successfully");

			} else {
				Department department = departments.stream().filter(dept -> dept.getId() == selectedDepartment.getId())
						.findAny().orElse(null);
				if (department == null) {
					departmentService.save(selectedDepartment);
				} else {
					department.setDepartmentName(selectedDepartment.getDepartmentName());
				}
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Department updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteDepartment() {

		departmentService.delete(selectedDepartment);
		this.departments.remove(this.selectedDepartment);
		if (this.selectedDepartments != null) {
			this.selectedDepartments.remove(this.selectedDepartment);
		}
		this.selectedDepartment = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Department deleted successfully");
	}

	public void deleteDepartments() {
		departmentService.deleteAll(this.selectedDepartments);
		this.departments.removeAll(this.selectedDepartments);
		this.selectedDepartments = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Departments deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteDepartmentsButtonMessage() {
		if (hasSelectedDepartments()) {
			int size = this.selectedDepartments.size();
			return size > 1 ? size + " departments selected" : "1 departments selected";
		}

		return "Delete";
	}

	public boolean hasSelectedDepartments() {
		return this.selectedDepartments != null && !this.selectedDepartments.isEmpty();
	}

	public void saveDesignation() {
		try {
			if (this.selectedDesignation.getId() == null) {
				designationService.save(selectedDesignation);
				this.designations.add(selectedDesignation);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Designation Added successfully");

			} else {
				Designation designation = designations.stream()
						.filter(desig -> desig.getId() == selectedDesignation.getId()).findAny().orElse(null);
				if (designation == null) {
					designationService.save(selectedDesignation);
				} else {
					designation.setDesignationName(selectedDesignation.getDesignationName());
				}
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Designation updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteDesignation() {

		designationService.delete(selectedDesignation);
		this.designations.remove(this.selectedDesignation);
		if (this.selectedDesignations != null) {
			this.selectedDesignations.remove(this.selectedDesignation);
		}
		this.selectedDesignation = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Designation deleted successfully");
	}

	public void deleteDesignations() {
		designationService.deleteAll(this.selectedDesignations);
		this.designations.removeAll(this.selectedDesignations);
		this.selectedDesignations = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Designations deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteDesignationsButtonMessage() {
		if (hasSelectedDesignations()) {
			int size = this.selectedDesignations.size();
			return size > 1 ? size + " designations selected" : "1 designation selected";
		}

		return "Delete";
	}

	public boolean hasSelectedDesignations() {
		return this.selectedDesignations != null && !this.selectedDesignations.isEmpty();
	}

	public void saveCadre() {
		try {
			if (this.selectedCadre.getCadreId() == null) {
				cadreService.save(selectedCadre);
				this.cadres.add(selectedCadre);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Cadre Added successfully");

			} else {
				Cadre cadre = cadres.stream().filter(cad -> cad.getCadreId() == selectedCadre.getCadreId()).findAny()
						.orElse(null);
				if (cadre == null) {
					cadreService.save(selectedCadre);
				} else {
					cadre.setCadre(selectedCadre.getCadre());
					cadre.setWagePeriod(selectedCadre.getWagePeriod());
					cadre.setCompOffFlag(selectedCadre.isCompOffFlag());
					cadre.setEarlyOutFlag(selectedCadre.isEarlyOutFlag());
					cadre.setLateInFlag(selectedCadre.isEarlyOutFlag());
					cadre.setOtFlag(selectedCadre.isOtFlag());
				}
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Cadre updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteCadre() {

		cadreService.delete(selectedCadre);
		this.cadres.remove(this.selectedCadre);
		if (this.selectedCadres != null) {
			this.selectedCadres.remove(this.selectedCadre);
		}
		this.selectedCadre = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Cadre deleted successfully");
	}

	public void deleteCadres() {
		cadreService.deleteAll(this.selectedCadres);
		this.cadres.removeAll(this.selectedCadres);
		this.selectedCadres = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Cadres deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteCadresButtonMessage() {
		if (hasSelectedCadres()) {
			int size = this.selectedCadres.size();
			return size > 1 ? size + " Cadres selected" : "1 cadre selected";
		}

		return "Delete";
	}

	public boolean hasSelectedCadres() {
		return this.selectedCadres != null && !this.selectedCadres.isEmpty();
	}

	public void saveShift() {
		try {
			if (this.selectedShift.getShiftId() == null) {
				shiftService.save(selectedShift);
				this.shifts.add(selectedShift);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Shift Added successfully");

			} else {
				Shift shift = shifts.stream().filter(shiftObj -> shiftObj.getShiftId() == selectedShift.getShiftId())
						.findAny().orElse(null);
				if (shift == null) {
					shiftService.save(selectedShift);
				} else {
					shift.setShiftName(selectedShift.getShiftName());
					shift.setShiftStart(selectedShift.getShiftStart());
					shift.setShiftEnd(selectedShift.getShiftEnd());
					shift.setLunchStart(selectedShift.getLunchStart());
					shift.setLunchEnd(selectedShift.getLunchEnd());

				}
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Shift updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteShift() {

		shiftService.delete(selectedShift);
		this.shifts.remove(this.selectedShift);
		if (this.selectedShifts != null) {
			this.selectedShifts.remove(this.selectedShift);
		}
		this.selectedShift = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Shift deleted successfully");
	}

	public void deleteShifts() {
		shiftService.deleteAll(this.selectedShifts);
		this.shifts.removeAll(this.selectedShifts);
		this.selectedShifts = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Shifts deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteShiftsButtonMessage() {
		if (hasSelectedShifts()) {
			int size = this.selectedShifts.size();
			return size > 1 ? size + " Shifts selected" : "1 shift selected";
		}

		return "Delete";
	}

	public boolean hasSelectedShifts() {
		return this.selectedShifts != null && !this.selectedShifts.isEmpty();
	}

	public void shiftType() {
		if (selectedEmployee.getShiftType().equals("F")) {
			objShifts = shifts.stream().map(Shift::getShiftId).collect(Collectors.toList());
		} else {
			objShifts = shifts.stream().map(shift -> shift.getShiftId().substring(0, 1)).distinct()
					.collect(Collectors.toList());
		}
	}

	public void saveEmployee() {
		try {
			if (this.selectedEmployee.getEmployeeId() == null) {
				employeeService.save(selectedEmployee);
				this.employees.add(selectedEmployee);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Employee Added successfully");

			} else {
				Employee employee = employees.stream()
						.filter(empObj -> empObj.getEmployeeId() == selectedEmployee.getEmployeeId()).findAny()
						.orElse(null);
				if (employee == null) {
					employeeService.save(selectedEmployee);
				} else {
					employee.setEmployeeName(selectedEmployee.getEmployeeName());
					employee.setAddress(selectedEmployee.getAddress());
					employee.setGender(selectedEmployee.getGender());
					employee.setMaritalStatus(selectedEmployee.getMaritalStatus());
					employee.setDateOfBirth(selectedEmployee.getDateOfBirth());
					employee.setDateOfJoining(selectedEmployee.getDateOfJoining());
					employee.setDateOfLeft(selectedEmployee.getDateOfLeft());
					employee.setEmail(selectedEmployee.getEmail());
					employee.setPhoneNumber(selectedEmployee.getPhoneNumber());
					employee.setShiftType(selectedEmployee.getShiftType());
					employee.setShift(selectedEmployee.getShift());
					employee.setDepartment(selectedEmployee.getDepartment());
					employee.setDesignation(selectedEmployee.getDesignation());
					employee.setCadre(selectedEmployee.getCadre());
				}
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Employee updated successfully");
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	public void deleteEmployee() {

		employeeService.delete(selectedEmployee);
		this.employees.remove(this.selectedEmployee);
		if (this.selectedEmployees != null) {
			this.selectedEmployees.remove(this.selectedEmployee);
		}
		this.selectedEmployee = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Employee deleted successfully");
	}

	public void deleteEmployees() {
		employeeService.deleteAll(this.selectedEmployees);
		this.employees.removeAll(this.selectedEmployees);
		this.selectedEmployees = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Employees deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteEmployeesButtonMessage() {
		if (hasSelectedEmployees()) {
			int size = this.selectedEmployees.size();
			return size > 1 ? size + " Employees selected" : "1 employee selected";
		}

		return "Delete";
	}

	public boolean hasSelectedEmployees() {
		return this.selectedEmployees != null && !this.selectedEmployees.isEmpty();
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
