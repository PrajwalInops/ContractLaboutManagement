package com.inops.visitorpass.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter{

	 private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.trim().isEmpty()) {
	            return null;
	        }

	        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	        try {
	            return dateFormat.parse(value);
	        } catch (ParseException e) {
	            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.", e);
	        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		  if (value == null) {
	            return "";
	        }

	        if (!(value instanceof LocalDate)) {
	            throw new IllegalArgumentException("This converter only handles instances of java.util.Date");
	        }

	        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	        return dateFormat.format((LocalDate) value);
	    }
	

}
