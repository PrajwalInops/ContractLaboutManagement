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

import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.service.IDepartment;

@Component
@FacesConverter(value = "departmentConverter", managed = true)
public class DepartmentConverter implements Converter<Department> {

	private List<Department> departments;

	@Inject
	private IDepartment departmentService;

	@PostConstruct
	public void init() {
		departments = departmentService.findAll().get();
	}

	@Override
	public Department getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				// return departments.stream().filter(dep -> dep.getId() ==
				// Long.valueOf(value)).findAny()
				return departments.stream().filter(dep -> dep.getId().equals(value)).findAny().orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Department."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Department value) {
		if (value != null) {
			// return String.valueOf(value.getDivisionId());
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
