package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Company;

public interface CompanyRepository  extends JpaRepository<Company,Long>{

}
