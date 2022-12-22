package com.inops.visitorpass.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Department;
import com.inops.visitorpass.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Report {

	private String reportName;
	private String reportType;
	private String selectionType;
    private List<LocalDate> dateRange;
	private List<Employee> employees;
	private List<Department> departments;
		
}
