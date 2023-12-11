package com.inops.visitorpass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "CompensatoryOff")
public class CompensatoryOff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String mode;
	private boolean enable;
	private int creditWeekend;
	private int creditHoliday;
	private int expiresIn;
	private String unitsAllowed;
	
	@Column(name = "durationAllowed", columnDefinition = "NVARCHAR(MAX)")
	private String durationAllowed;

	public void setId(Long id) {
		this.id = id;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setCreditWeekend(int creditWeekend) {
		this.creditWeekend = creditWeekend;
	}

	public void setCreditHoliday(int creditHoliday) {
		this.creditHoliday = creditHoliday;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setUnitsAllowed(String unitsAllowed) {
		this.unitsAllowed = unitsAllowed;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDurationAllowed(String durationAllowed) {
		this.durationAllowed = durationAllowed;
	}


}
