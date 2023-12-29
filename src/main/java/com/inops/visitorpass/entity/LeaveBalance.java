package com.inops.visitorpass.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "LeaveBalance")
public class LeaveBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long leaveBalanceId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "employeeId", nullable = false, updatable = true)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "leaveTypeId", nullable = false, updatable = true)
	private LeaveTypeEntity leaveType;
	
	private Date creditDate;	
	private double previousBalance;
	private double earnedBalance;
	private double redeemedBalance;
	private double closingBalance;
	private double carryForwardBalance;
	private double encashedBalance;
	
	@OneToMany(mappedBy = "leaveBalance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<LeaveBalanceHistory> leaveBalanceHistorys;

	public void setLeaveBalanceId(Long leaveBalanceId) {
		this.leaveBalanceId = leaveBalanceId;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setLeaveType(LeaveTypeEntity leaveType) {
		this.leaveType = leaveType;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public void setPreviousBalance(double previousBalance) {
		this.previousBalance = previousBalance;
	}

	public void setEarnedBalance(double earnedBalance) {
		this.earnedBalance = earnedBalance;
	}

	public void setRedeemedBalance(double redeemedBalance) {
		this.redeemedBalance = redeemedBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public void setCarryForwardBalance(double carryForwardBalance) {
		this.carryForwardBalance = carryForwardBalance;
	}

	public void setEncashedBalance(double encashedBalance) {
		this.encashedBalance = encashedBalance;
	}

	public void setLeaveBalanceHistorys(Set<LeaveBalanceHistory> leaveBalanceHistorys) {
		this.leaveBalanceHistorys = leaveBalanceHistorys;
	}

	
	
}
