package com.inops.visitorpass.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LeaveBalanceHistory")
public class LeaveBalanceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long balanceHistoryId;
	
	private int month;
	private int year;
	private Date creditDate;	
	private double previousBalance;
	private double earnedBalance;
	private double redeemedBalance;
	private double closingBalance;
	private double carryForwardBalance;
	private double encashedBalance;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "leaveBalanceId", nullable = false, updatable = true)
	private LeaveBalance leaveBalance;

	public void setBalanceHistoryId(Long balanceHistoryId) {
		this.balanceHistoryId = balanceHistoryId;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
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

	public void setLeaveBalance(LeaveBalance leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
		
	
}
