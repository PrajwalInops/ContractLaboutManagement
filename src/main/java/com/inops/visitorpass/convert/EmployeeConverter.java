package com.inops.visitorpass.convert;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.service.IEmployee;

@Component
@FacesConverter(value = "employeeConverter", managed = true)
public class EmployeeConverter implements Converter<Employee> {

	private List<Employee> employees;

	@Inject
	private IEmployee employeeService;

	@PostConstruct
	public void init() {
		employees = employeeService.findAll().get();
	}

	@Override
	public Employee getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				Employee emp =  employees.stream().filter(empl -> empl.getEmployeeId().equals(value)).findAny().orElse(null);
				return emp;
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid employee."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Employee value) {
		if (value != null) {
			return value.getEmployeeId();
		} else {
			return null;
		}
	}

}
