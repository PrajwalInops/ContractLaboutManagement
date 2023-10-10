package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Shift;
import com.inops.visitorpass.repository.ShiftRepository;
import com.inops.visitorpass.service.IShift;

import lombok.RequiredArgsConstructor;

@Service("shiftService")
@RequiredArgsConstructor
public class ShiftService implements IShift {

	private final ShiftRepository shiftRepository;

	@Override
	public Optional<List<Shift>> findAll() {
		return Optional.ofNullable(shiftRepository.findAll());
	}

	@Override
	public Optional<Shift> findById(String id) {
		return shiftRepository.findById(id);
	}

	@Override
	public Shift save(Shift shift) {
		return shiftRepository.save(shift);
	}

	@Override
	public void delete(Shift shift) {
		shiftRepository.delete(shift);
	}

	@Override
	public void deleteAll(List<Shift> shifts) {
		shiftRepository.deleteAll(shifts);
	}

}
