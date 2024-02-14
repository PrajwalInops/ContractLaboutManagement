package com.inops.visitorpass.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.ICadre;
import com.inops.visitorpass.service.IDepartment;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IVisitorService;

@Configuration
public class VisitorConfig {

	@Autowired
	private IDepartment departmentService;

	@Autowired
	private IEmployee employeeService;

	@Autowired
	private ICadre cadreService;

	// @Autowired
	private IVisitorService visitorService;

	@Bean
	@DependsOn(value = "departmentService")
	public Optional<List<Department>> getDepartments() {
		return departmentService.findAll();
	}

	@Bean
	@DependsOn(value = "employeeService")
	public Optional<List<Employee>> getEmployees() {
		return employeeService.findAll();
	}

	@Bean
	@DependsOn(value = "cadreService")
	public Optional<List<Cadre>> getCadres() {
		return cadreService.findAll();
	}

	// @Bean
	// @DependsOn(value = "visitorService")
	public Optional<List<Visitor>> getVisitors() {
		return visitorService.findAll();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
