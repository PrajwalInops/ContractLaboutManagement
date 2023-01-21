package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.ReaderIpAddress;
import com.inops.visitorpass.repository.ReaderIpAddressRepository;
import com.inops.visitorpass.service.IReaderIpAddress;

@Service("readerIpAddress")
public class ReaderIpAddressService implements IReaderIpAddress {

	private final ReaderIpAddressRepository readerIpAddressRepository;

	public ReaderIpAddressService(ReaderIpAddressRepository readerIpAddressRepository) {
		super();
		this.readerIpAddressRepository = readerIpAddressRepository;
	}

	@Override
	public Optional<List<ReaderIpAddress>> findAll() {

		return Optional.of(readerIpAddressRepository.findAll());
	}

}
