package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Shift;
import com.inops.visitorpass.repository.ShiftRepository;
import com.inops.visitorpass.service.IShift;

@Service("shiftService")
public class ShiftService implements IShift {

	private ShiftRepository shiftRepository;

	public ShiftService(ShiftRepository shiftRepository) {
		super();
		this.shiftRepository = shiftRepository;
	}

	@Override
	public Optional<List<Shift>> findAll() {
		return Optional.ofNullable(shiftRepository.findAll());
	}

}
