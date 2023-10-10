package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Shift;

public interface IShift {

	Optional<Shift> findById(String id);

	Optional<List<Shift>> findAll();

	Shift save(Shift shift);

	void delete(Shift shift);

	void deleteAll(List<Shift> shifts);

}
