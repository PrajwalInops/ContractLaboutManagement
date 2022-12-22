package com.inops.visitorpass.convert;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.IVisitorService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@Component
@FacesConverter(value = "visitorConverter", managed = true)
public class VisitorConverter implements Converter<Visitor>{

	@Inject
	private IVisitorService visitorService;
	
	@Override
	public Visitor getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value != null && value.trim().length() > 0) {
	            try {
	                return visitorService.getCountriesAsMap().get(value);
	            }
	            catch (NumberFormatException e) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
	            }
	        }
	        else {
	            return null;
	        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Visitor value) {
		if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
	}

}
