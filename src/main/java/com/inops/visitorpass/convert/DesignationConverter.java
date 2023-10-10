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

import com.inops.visitorpass.entity.Designation;
import com.inops.visitorpass.service.IDesignation;

@Component
@FacesConverter(value = "designationConverter", managed = true)
public class DesignationConverter implements Converter<Designation> {

	private List<Designation> designations;

	@Inject
	private IDesignation designationService;

	@PostConstruct
	public void init() {
		designations = designationService.findAll().get();
	}

	@Override
	public Designation getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				// return departments.stream().filter(dep -> dep.getId() ==
				// Long.valueOf(value)).findAny()
				return designations.stream().filter(des -> des.getId().equals(value)).findAny().orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Designation."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Designation value) {
		if (value != null) {
			// return String.valueOf(value.getDivisionId());
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
