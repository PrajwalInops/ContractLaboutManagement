package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Organigram;

public interface IOrganigram {

	Optional<List<Organigram>> findAll();
	
	Organigram save(Organigram organigram);

	void delete(Organigram organigram);

	void deleteAll(List<Organigram> organigrams);
}
