package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Organigram;
import com.inops.visitorpass.repository.OrganigramRepository;
import com.inops.visitorpass.service.IOrganigram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Service("organigramService")
@RequiredArgsConstructor
public class OrganigramService implements IOrganigram {

	private final OrganigramRepository organigramRepository;

	@Override
	public Optional<List<Organigram>> findAll() {
		// TODO Auto-generated method stub
		return Optional.of(organigramRepository.findAll());
	}

	@Override
	public Organigram save(Organigram organigram) {
		return organigramRepository.save(organigram);
	}

	@Override
	public void delete(Organigram organigram) {
		organigramRepository.delete(organigram);
	}

	@Override
	public void deleteAll(List<Organigram> organigrams) {
		organigramRepository.deleteAll(organigrams);
	}

}
