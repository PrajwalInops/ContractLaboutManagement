package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "tblshifts")
public class Shift {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shiftid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String shiftId;
	
	@Column(name = "shiftname")
	private String shiftName;
	
	@Transient
	@Column(name = "shiftStart")
	private Date shiftStart;
	
	@Transient
	@Column(name = "shiftEnd")
	private Date shiftEnd;	
	
	@Transient
	@Column(name = "lunchStart")
	private Date lunchStart;
	
	@Transient
	@Column(name = "lunchEnd")
	private Date lunchEnd;
		
}