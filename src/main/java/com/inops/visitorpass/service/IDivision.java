package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Division;

public interface IDivision {

	Optional<List<Division>> findAll();
}
