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
import com.inops.visitorpass.entity.Entitlement;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IEntitlement;

@Component
@FacesConverter(value = "entitlementConverter", managed = true)
public class EntitlementConverter implements Converter<Entitlement> {

	private List<Entitlement> entitlements;

	@Inject
	private IEntitlement entitlementServices;

	@PostConstruct
	public void init() {
		entitlements = entitlementServices.findAll().get();
	}

	@Override
	public Entitlement getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return entitlements.stream().filter(ent -> ent.getEntitlementId() == Long.parseLong(value)).findAny()
						.orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid employee."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Entitlement value) {
		if (value != null) {
			return String.valueOf(value.getEntitlementId());
		} else {
			return null;
		}
	}

}
