package com.inops.visitorpass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.Cards;
import com.inops.visitorpass.entity.Division;

public interface CardsRepository extends JpaRepository<Cards, Long> {

	Optional<List<Cards>> findAllByDivision(Division divisionId);
}
