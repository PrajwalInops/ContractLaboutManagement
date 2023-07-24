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

import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.LeaveTypeEntity;
import com.inops.visitorpass.service.ILeaveType;

@Component
@FacesConverter(value = "leaveTypeConverter", managed = true)
public class LeaveTypeConverter implements Converter<LeaveTypeEntity> {

	private Optional<List<LeaveTypeEntity>> leaveTypes;

	@Inject
	private ILeaveType leaveTypeService;

	@PostConstruct
	public void init() {
		leaveTypes = leaveTypeService.findAll();
	}

	@Override
	public LeaveTypeEntity getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return leaveTypes.get().stream().filter(leave -> leave.getLeaveTypeId() == Long.parseLong(value))
						.findAny().orElse(null);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid visitor."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LeaveTypeEntity value) {
		if (value != null) {
			return String.valueOf(value.getLeaveTypeId());
		} else {
			return null;
		}
	}

}
