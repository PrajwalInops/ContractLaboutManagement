package com.inops.visitorpass.convert;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.domain.Kvp;
import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.entity.Employee;

@Component
@FacesConverter(value = "kvpConverter", managed = true)
public class KvpConverter implements Converter<Kvp> {

	@Autowired
	ApplicationContext ctx;

	Optional<List<Department>> getDepartments;
	Optional<List<Employee>> getEmployees;

	@PostConstruct
	public void init() {
		getDepartments = (Optional<List<Department>>) this.ctx.getBean("getDepartments");
		getEmployees = (Optional<List<Employee>>) this.ctx.getBean("getEmployees");
	}

	@Override
	public Kvp getAsObject(FacesContext context, UIComponent component, String value) {
		Kvp kvp = null;
		if (value != null && value.trim().length() > 0) {
			try {
				Optional<Department> department = Optional.ofNullable(getDepartments.get().stream()
						.filter(dept -> dept.getId().equals(value)).findAny().orElse(null));
				if (department.isPresent()) {
					kvp = new Kvp(department.get().getId(), department.get().getDepartmentName());
				}
				Optional<Employee> employee = Optional.ofNullable(getEmployees.get().stream()
						.filter(emp -> emp.getEmployeeId().equals(value)).findAny().orElse(null));
				if (employee.isPresent()) {
					kvp = new Kvp(employee.get().getEmployeeId(), employee.get().getEmployeeName());
				}
				
				return kvp;
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid locale."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Kvp value) {
		if (value != null) {
			return String.valueOf(value.getKey());
		} else {
			return null;
		}
	}

}
