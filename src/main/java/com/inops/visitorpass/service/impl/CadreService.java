package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Cadre;
import com.inops.visitorpass.repository.CadreRepository;
import com.inops.visitorpass.service.ICadre;

@Service("cadreService")
public class CadreService implements ICadre {

	private CadreRepository cadreRepository;

	public CadreService(CadreRepository cadreRepository) {
		super();
		this.cadreRepository = cadreRepository;
	}

	@Override
	public Optional<List<Cadre>> findAll() {
		return Optional.ofNullable(cadreRepository.findAll());
	}

}
