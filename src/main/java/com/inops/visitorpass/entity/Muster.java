package com.inops.visitorpass.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "TblMuster")
public class Muster {

	@EmbeddedId
	private MusterId musterId;

	@Column(name = "Shiftid")
	private String ShiftId;

	@Column(name = "Attid")
	private String attendanceId;

	@Column(name = "Leavetypeid")
	private String leaveTypeId;

	@Column(name = "Hrsworked")
	private int hoursWorked;

	@Column(name = "Extrahours")
	private int extraHours;

	@Column(name = "Latepunch")
	private int latePunch;

	@Column(name = "Earlyout")
	private int earlyOut;

	@Column(name = "Firstin")
	private String firstInPunch;

	@Column(name = "Lastout")
	private String lastOutPunch;

}
