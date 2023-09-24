package com.inops.visitorpass.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Shift;
import com.inops.visitorpass.service.IDepartment;
import com.inops.visitorpass.service.IDivision;

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

	private Division selectedDivision;
	private List<Division> selectedDivisions;
	private List<Division> divisions;

	private Department selectedDepartment;
	private List<Department> selectedDepartments;
	private List<Department> departments;

	private Cadre selectedCadre;
	private List<Cadre> selectedCadres;

	private Shift selectedShift;
	private List<Shift> selectedShifts;

	@PostConstruct
	public void init() {

		divisions = division.findAll().get();
		departments = departmentService.findAll().get();

	}

	public void openNew() {
		this.selectedDivision = new Division();
		this.selectedDepartment = new Department();

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
		this.divisions.remove(this.selectedDivision);
		if (this.selectedDivisions != null) {
			this.selectedDivisions.remove(this.selectedDivision);
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

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
