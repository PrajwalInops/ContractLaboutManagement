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

import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.service.IDivision;

@Component
@FacesConverter(value = "divisionConverter", managed = true)
public class DivisionConverter implements Converter<Division> {

	private List<Division> divisions;

	@Inject
	private IDivision division;

	@PostConstruct
	public void init() {
		divisions = division.findAll().get();
	}

	@Override
	public Division getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return divisions.stream().filter(div -> div.getDivisionId() == Long.valueOf(value)).findAny()
						.orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Division."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Division value) {
		if (value != null) {
			return String.valueOf(value.getDivisionId());
		} else {
			return null;
		}
	}

}
