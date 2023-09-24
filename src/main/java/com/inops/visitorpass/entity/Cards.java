package com.inops.visitorpass.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblcards")
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cardNo;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "divisionId", nullable = false, updatable = true)
	private Division division;

	public void setId(Long id) {
		this.id = id;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	
}
