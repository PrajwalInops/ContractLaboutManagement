package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Cadre;

public interface ICadre {

	Optional<List<Cadre>> findAll();

}
