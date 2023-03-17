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
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.IVisitorService;

//@Component
//@FacesConverter(value = "visitorConverter", managed = true)
public class VisitorConverter implements Converter<Visitor> {

	@Autowired
	ApplicationContext ctx;

	private Optional<List<Visitor>> visitors;

	@PostConstruct
	public void init() {
		visitors = (Optional<List<Visitor>>) ctx.getBean("getVisitors");
	}

	@Inject
	private IVisitorService visitorService;

	@Override
	public Visitor getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return visitors.get().stream().filter(visit -> visit.getMobileNo().equals(value)).findAny()
						.orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid visitor."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Visitor value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
