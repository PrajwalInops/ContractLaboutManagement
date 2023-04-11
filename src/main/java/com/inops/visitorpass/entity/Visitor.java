package com.inops.visitorpass.entity;

import java.io.Serializable;
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
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
//@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Visitor")
public class Visitor implements Serializable{

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
	private String outOrInPass;
	private long division;

	public void setId(long id) {
		this.id = id;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public void setBadgeNo(String badgeNo) {
		this.badgeNo = badgeNo;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNoOfPersons(String noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}

	public void setLaptopToBePermitted(String laptopToBePermitted) {
		this.laptopToBePermitted = laptopToBePermitted;
	}

	public void setOtherMediaItems(String otherMediaItems) {
		this.otherMediaItems = otherMediaItems;
	}

	public void setVisitingDepartment(String visitingDepartment) {
		this.visitingDepartment = visitingDepartment;
	}

	public void setVisitingEmployee(String visitingEmployee) {
		this.visitingEmployee = visitingEmployee;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setVisitorPhoto(String visitorPhoto) {
		this.visitorPhoto = visitorPhoto;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setOutOrInPass(String outOrInPass) {
		this.outOrInPass = outOrInPass;
	}

	public void setDivision(long division) {
		this.division = division;
	}

}
