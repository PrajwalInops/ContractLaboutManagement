package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.Company;

public interface ICompany {

	Optional<List<Company>> findAll();
}
