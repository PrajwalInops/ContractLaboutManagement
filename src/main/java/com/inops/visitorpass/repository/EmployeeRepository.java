package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee,String> {

}
