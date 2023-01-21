package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Cards;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.repository.CardsRepository;
import com.inops.visitorpass.service.ICard;

@Service("card")
public class CardService implements ICard {

	private CardsRepository cardsRepository;

	public CardService(CardsRepository cardsRepository) {
		super();
		this.cardsRepository = cardsRepository;
	}

	@Override
	public Optional<List<Cards>> findAll() {
		return Optional.ofNullable(cardsRepository.findAll());
	}

	@Override
	public Optional<List<Cards>> findAllByDivisionId(Division division) {
		return cardsRepository.findAllByDivision(division);
	}

}
