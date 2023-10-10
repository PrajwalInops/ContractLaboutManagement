package com.inops.visitorpass.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "tblempmast")
public class Employee {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empid")
	private String employeeId;
	
	@Column(name = "empname")
	private String employeeName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptid")
    private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "desigid")
    private Designation designation;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cadreid")
    private Cadre cadre;
	
	//@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "shiftid")
	@Column(name = "shiftid")
    private String shift;
	
	@Transient
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "divisionId")
    private Division division;


	@Column(name = "shifttype")
	private String shiftType;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "sex")
	private String gender;
	
	@Column(name = "Marital_stat")
	private String maritalStatus;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	@Column(name = "doj")
	private LocalDate dateOfJoining;
	
	@Column(name = "dol")
	private LocalDate dateOfLeft;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phoneno")
	private String phoneNumber;

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setDateOfLeft(LocalDate dateOfLeft) {
		this.dateOfLeft = dateOfLeft;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
