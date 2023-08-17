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
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CompensatoryOff")
public class CompensatoryOff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mode;
    private boolean enable;
    private int creditWeekend;
    private int creditHoliday;
    private int expiresIn;
    private String unitsAllowed;
    private String durationAllowed;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "divisionId", nullable = false)
	private Division division;

}
