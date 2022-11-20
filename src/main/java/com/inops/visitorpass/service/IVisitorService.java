package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Visitor;

public interface IVisitorService {


	Optional<Visitor> findById(long id);

	Optional<Visitor> findByMobileNo(String mobileNo);
	
	Optional<List<Visitor>> findAll();
	
	void save(Visitor visitor);
	
	void update(Visitor visitor);
	
	void delete(String mobileNumber);
}
