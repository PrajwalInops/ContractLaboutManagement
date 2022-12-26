package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Company;
import com.inops.visitorpass.repository.CompanyRepository;
import com.inops.visitorpass.service.ICompany;

@Service("company")
public class CompanyService implements ICompany {

	private CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public Optional<List<Company>> findAll() {
		return Optional.ofNullable(companyRepository.findAll());
	}

}
