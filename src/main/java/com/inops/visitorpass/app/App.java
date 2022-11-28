package com.inops.visitorpass.app;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import org.primefaces.context.PrimeApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.domain.Country;

@Component
@Scope("session")
public class App implements Serializable {

	private String theme = "saga";
	private boolean darkMode = false;
	private String inputStyle = "outlined";
	private Country locale = new Country(0, Locale.US);

	public String getTheme() {
		return theme;
	}

	public boolean isDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getInputStyle() {
		return inputStyle;
	}

	public void setInputStyle(String inputStyle) {
		this.inputStyle = inputStyle;
	}

	public String getInputStyleClass() {
		return "filled".equals(this.inputStyle) ? "ui-input-filled" : "";
	}

	public Country getLocale() {
		return locale;
	}

	public void setLocale(Country locale) {
		this.locale = locale;
	}

	public void changeTheme(String theme, boolean darkMode) {
		this.theme = theme;
		this.darkMode = darkMode;
	}

	public String getThemeImage() {
		String result = getTheme();
		switch (result) {
		case "nova-light":
			result = "nova.png";
			break;
		case "nova-colored":
			result = "nova-accent.png";
			break;
		case "nova-dark":
			result = "nova-alt.png";
			break;
		case "bootstrap4-blue-light":
			result = "bootstrap4-light-blue.svg";
			break;
		case "bootstrap4-blue-dark":
			result = "bootstrap4-dark-blue.svg";
			break;
		case "bootstrap4-purple-light":
			result = "bootstrap4-light-purple.svg";
			break;
		case "bootstrap4-purple-dark":
			result = "bootstrap4-dark-purple.svg";
			break;
		default:
			result += ".png";
			break;
		}
		return result;
	}

	public String getPrimeFacesVersion() {
		return PrimeApplicationContext.getCurrentInstance(FacesContext.getCurrentInstance()).getEnvironment()
				.getBuildVersion();
	}
}
