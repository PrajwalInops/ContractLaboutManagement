package com.inops.visitorpass.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.service.IUserService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("userController")
@Scope("session")
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}

	private String emailId;
	private String oldPassword;
	private String newPassword;

	public void resetPassword() {
		try {
			boolean isReset = userService.resetPassword(new ResetPassword(emailId, oldPassword, newPassword));
			if (isReset)
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Password changed successfully for: " + emailId);
			else
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Old Password not matching for : " + emailId);
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Failed to reset password for : " + emailId);
			log.error("exception at the time of resetPassword {}", e);
		}
		cleanUp();
	}
	
	private void cleanUp()
	{
		setEmailId(null);
		setNewPassword(null);
		setOldPassword(null);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
