package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.ReaderIpAddress;

public interface IReaderIpAddress {

	Optional<List<ReaderIpAddress>> findAll();
}
