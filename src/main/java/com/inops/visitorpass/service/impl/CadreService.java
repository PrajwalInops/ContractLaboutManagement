package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.repository.CadreRepository;
import com.inops.visitorpass.service.ICadre;

import lombok.AllArgsConstructor;

@Service("cadreService")
@AllArgsConstructor
public class CadreService implements ICadre {

	private final CadreRepository cadreRepository;

	@Override
	public Optional<List<Cadre>> findAll() {
		return Optional.ofNullable(cadreRepository.findAll());
	}

	@Override
	public Optional<Cadre> findById(String id) {
		return cadreRepository.findById(id);
	}

	@Override
	public Cadre save(Cadre cadre) {
		return cadreRepository.save(cadre);
	}

	@Override
	public void delete(Cadre cadre) {
		cadreRepository.delete(cadre);

	}

	@Override
	public void deleteAll(List<Cadre> cadres) {
		cadreRepository.deleteAll(cadres);

	}

}
