package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Shift;

public interface IShift {

	Optional<List<Shift>> findAll();

}
