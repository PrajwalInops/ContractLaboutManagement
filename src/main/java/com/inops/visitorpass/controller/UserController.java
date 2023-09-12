package com.inops.visitorpass.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.User;
import com.inops.visitorpass.model.Role;
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

	@Autowired
	ApplicationContext ctx;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}

	private List<Employee> employees;
	private List<User> users;
	private String employeeId;
	private String role;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String ip;
	private boolean rest;
	private User user;

	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (User) auth.getPrincipal();
		if (user.getRole().equals(Role.SUPER_USER)) {
			users = userService.findAll();
			rest = true;
		} else {			
			users = new ArrayList<>();
			users.add(user);
		}

		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
	}

	private String emailId;
	private String oldPassword;
	private String newPassword;

	public void resetPassword() {
		try {
			boolean isReset = userService.resetPassword(new ResetPassword(emailId, oldPassword, newPassword));
			if (isReset)
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Password reset successfully for: " + emailId);
			else
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Failed to reset password for : " + emailId);
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Failed to reset password for : " + emailId);
			log.error("exception at the time of resetPassword {}", e);
		}
		cleanUp();
	}
	
	public void changePassword() {
		try {
			boolean isReset = userService.changePassword(new ResetPassword(emailId, oldPassword, newPassword));
			if (isReset)
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Password changed successfully for: " + emailId+" Please re-login");
			else
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Old Password not matching for : " + emailId);
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Failed to change password for : " + emailId);
			log.error("exception at the time of resetPassword {}", e);
		}
		cleanUp();
	}

	private void cleanUp() {
		setEmailId(null);
		setNewPassword(null);
		setOldPassword(null);
	}

	public void getUserDetails() {

		User usr = users.stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
		if (usr != null) {
			setIp(usr.getSystemIpAddress());
			setEmployeeId(usr.getEmployee().getEmployeeId());
			setRole(usr.getRole().getValue());
			setFirstName(usr.getFirstName());
			setLastName(usr.getLastName());
		}
	}

	public void saveUser() {
		User user = new User();
		user.setEmail(email);
		user.setRole(Role.valueOf(role));
		user.setPassword(password);
		user.setEmployee(employees.stream().filter(employees -> employees.getEmployeeId().equals(employeeId)).findAny()
				.orElse(null));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSystemIpAddress(ip);

		userService.saveUser(user);

		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "user created successfully for: " + emailId);
		cleanUser();
	}

	public void updateUser() {
		User user = users.stream().filter(usr -> usr.getEmail().equals(email)).findAny().orElse(null);
		user.setEmail(email);
		user.setRole(Role.valueOf(role));
		user.setPassword(password);
		user.setEmployee(employees.stream().filter(employees -> employees.getEmployeeId().equals(employeeId)).findAny()
				.orElse(null));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSystemIpAddress(ip);

		userService.saveUser(user);
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "user updated successfully for: " + emailId);
		cleanUser();
	}

	public void cleanUser() {
		setIp(null);
		setEmployeeId(null);
		setRole(null);
		setFirstName(null);
		setLastName(null);
		setPassword(null);
		setEmail(null);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public boolean rest() {
		return rest;
	}

	public void setRest(boolean isRest) {
		this.rest = isRest;
	}

}
