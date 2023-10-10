package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Cadre;

public interface ICadre {

	Optional<Cadre> findById(String id);

	Optional<List<Cadre>> findAll();

	Cadre save(Cadre cadre);

	void delete(Cadre cadre);

	void deleteAll(List<Cadre> cadres);

}
