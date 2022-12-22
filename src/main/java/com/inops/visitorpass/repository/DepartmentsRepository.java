package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Department;

public interface DepartmentsRepository extends JpaRepository<Department,String>{

}
