package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Organigram;

public interface OrganigramRepository extends JpaRepository<Organigram, Long> {

}
