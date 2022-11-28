package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Visitor")
public class Visitor {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String mobileNo;
	private Date date;
	private String visitorId;
	private String badgeNo;
	private String visitorName;
	private String company;
	private String address;
	private String noOfPersons;
	private String nationality;
	private String purpose;
	private String idProof;
	private String idProofNo;
	private String laptopToBePermitted;
	private String otherMediaItems;
	private String visitingDepartment;
	private String visitingEmployee;
	private String remarks;
	private String visitorPhoto;
	private boolean isApproved;
}
