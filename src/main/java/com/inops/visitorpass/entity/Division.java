package com.inops.visitorpass.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "divisions")
public class Division {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long divisionId;
	
	private String divisionName;

	@OneToMany(mappedBy = "division", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Cards> cards;

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public void setCards(Set<Cards> cards) {
		this.cards = cards;
	}

	
}
