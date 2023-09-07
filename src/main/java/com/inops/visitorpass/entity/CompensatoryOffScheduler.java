package com.inops.visitorpass.entity;

import java.util.Date;

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
@Table(name = "CompensatoryOffScheduler")
public class CompensatoryOffScheduler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Date timeToSchedule;
	private int dateBefore;
	private boolean includeOverTime;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "divisionId", nullable = true)
	private Division division;

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTimeToSchedule(Date timeToSchedule) {
		this.timeToSchedule = timeToSchedule;
	}

	public void setDateBefore(int dateBefore) {
		this.dateBefore = dateBefore;
	}

	public void setIncludeOverTime(boolean includeOverTime) {
		this.includeOverTime = includeOverTime;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
	
	

}
