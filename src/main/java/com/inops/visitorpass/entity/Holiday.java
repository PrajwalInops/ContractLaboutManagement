package com.inops.visitorpass.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "holiday")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long holidayId;

	private String holidayName;
	private Date holidayDate;
	private String holidayDescription;
	private int holidayYear;
	private String holidayType;
	private int reminderBefore;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "Holiday_Divisions", joinColumns = @JoinColumn(name = "holiday_Id"), inverseJoinColumns = @JoinColumn(name = "division_Id"))
	private List<Division> divisions;

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	public void setHolidayYear(int holidayYear) {
		this.holidayYear = holidayYear;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public void setReminderBefore(int reminderBefore) {
		this.reminderBefore = reminderBefore;
	}

	public void setHolidayId(long holidayId) {
		this.holidayId = holidayId;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}

}
