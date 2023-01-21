package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Cards;
import com.inops.visitorpass.entity.Division;

public interface ICard {

	Optional<List<Cards>> findAll();
	
	Optional<List<Cards>> findAllByDivisionId(Division divisionId);
}
