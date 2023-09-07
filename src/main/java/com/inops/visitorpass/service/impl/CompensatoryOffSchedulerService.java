package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.CompensatoryOffScheduler;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.repository.CompensatorySchedulerRepository;
import com.inops.visitorpass.service.ICompensatoryOffScheduler;

import lombok.RequiredArgsConstructor;

@Service("compensatoryOffSchedulerService")
@RequiredArgsConstructor
public class CompensatoryOffSchedulerService implements ICompensatoryOffScheduler {

	private final CompensatorySchedulerRepository compensatoryRepository;


	@Override
	public Optional<List<CompensatoryOffScheduler>> findAll() {
		return Optional.of(compensatoryRepository.findAll());
	}

	@Override
	public Optional<List<CompensatoryOffScheduler>> findAllByDivisionId(Division division) {
		return compensatoryRepository.findAllByDivision(division);
	}

	@Override
	public CompensatoryOffScheduler save(CompensatoryOffScheduler scheduler) {
		
		return compensatoryRepository.save(scheduler);
	}

	@Override
	public void delete(CompensatoryOffScheduler scheduler) {
		compensatoryRepository.delete(scheduler);
		
	}

	@Override
	public void deleteAll(List<CompensatoryOffScheduler> schedulers) {
		compensatoryRepository.deleteAll(schedulers);
		
	}

}
