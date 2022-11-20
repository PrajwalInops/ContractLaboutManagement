package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.repository.VisitorRepository;
import com.inops.visitorpass.service.IVisitorService;

@Service("visitorService")
public class VisitorServiceImpl implements IVisitorService {

	private VisitorRepository visitorRepository;

	public VisitorServiceImpl(VisitorRepository visitorRepository) {
		super();
		this.visitorRepository = visitorRepository;
	}

	@Override
	public Optional<Visitor> findById(long id) {
		// TODO Auto-generated method stub
		Optional<Visitor> visitor = visitorRepository.findById(id);
		return visitor;
	}

	@Override
	public Optional<List<Visitor>> findAll() {
		return Optional.of(visitorRepository.findAll());
	}

	@Override
	public void save(Visitor visitor) {
		visitorRepository.save(visitor);
	}

	@Override
	public Optional<Visitor> findByMobileNo(String mobileNo) {
		return visitorRepository.findByMobileNo(mobileNo);
	}

	@Override
	public void update(Visitor visitor) {
		
		visitor.setId(visitorRepository.findByMobileNo(visitor.getMobileNo()).get().getId());
		
		visitorRepository.save(visitor);
	}

	@Override
	public void delete(String mobileNumber) {
		long id = visitorRepository.findByMobileNo(mobileNumber).get().getId();
		visitorRepository.deleteById(id);		
	}

}
