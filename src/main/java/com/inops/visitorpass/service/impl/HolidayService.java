package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Holiday;
import com.inops.visitorpass.repository.HolidayRepository;
import com.inops.visitorpass.service.IHoliday;

import lombok.RequiredArgsConstructor;

@Service("holidaySevice")
@RequiredArgsConstructor
public class HolidayService implements IHoliday {

	private final HolidayRepository holidayRepository;

	@Override
	public Optional<List<Holiday>> findAll() {
		return Optional.ofNullable(holidayRepository.findAll());
	}

	@Override
	public Optional<List<Holiday>> findAllByDivisionId(Division division) {
		return holidayRepository.findAllByDivision(division);
	}

}