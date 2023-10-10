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

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.service.ICadre;

@Component
@FacesConverter(value = "cadreConverter", managed = true)
public class CadreConverter implements Converter<Cadre> {

	private List<Cadre> cadres;

	@Inject
	private ICadre cadreService;

	@PostConstruct
	public void init() {
		cadres = cadreService.findAll().get();
	}

	@Override
	public Cadre getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				// return departments.stream().filter(dep -> dep.getId() ==
				// Long.valueOf(value)).findAny()
				return cadres.stream().filter(cad -> cad.getCadreId().equals(value)).findAny().orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Cadre."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cadre value) {
		if (value != null) {
			// return String.valueOf(value.getDivisionId());
			return String.valueOf(value.getCadreId());
		} else {
			return null;
		}
	}

}
