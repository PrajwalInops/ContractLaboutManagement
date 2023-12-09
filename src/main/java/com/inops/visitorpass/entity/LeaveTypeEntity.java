package com.inops.visitorpass.entity;

import java.io.Serializable;
import java.util.Date;

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
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LeaveType")
public class LeaveTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long leaveTypeId;

	private String leaveName;
	private String leaveCode;
	private String leaveType;
	private String leaveUnits;
	private String leaveDescription;
	private long entitlementRoleId;
	private long divisionId;
	private String employeeId;
	private String applicableGender;
	private String applicableMaritalStatus;
	private String leaveBalanceBasedOn;
	private String applicableFor;
	private int leaveCount;
	private int newJoinee;
	private int afterSixMonths;

	private Date validFrom;
	private Date validTo;
	private boolean quarterDay;
	private boolean halfDay;
	private boolean beyondPermittedLeave;
	private boolean considerDoj;
	private boolean roundOffPermittedLeave;
	private boolean excludeHolidays;
	private boolean excludeWeekEndsDays;
	private boolean includeAllHolidaysAndWeeklyOffs;
	private boolean carryForward;
	private boolean reset;
	private boolean encashment;

	private String probationPeriod;

	private float businessPeriod;
	private int duringProbation;
	private int joiningMonthLesserThan;
	private int joiningMonthGreaterThan;
	private int maxconsiguitiveLeaves;
	private int consigutiveDaysExceeds;
	private int applicationSubmitBefore;

	private String resetType;
	private String resetOn;
	private String resetMonth;

	private int maxAccumulationCount;
	private int encashmentMaxLimit;

	public void setLeaveTypeId(long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public void setLeaveUnits(String leaveUnits) {
		this.leaveUnits = leaveUnits;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public void setEntitlementRoleId(long entitlementRoleId) {
		this.entitlementRoleId = entitlementRoleId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setApplicableGender(String applicableGender) {
		this.applicableGender = applicableGender;
	}

	public void setApplicableMaritalStatus(String applicableMaritalStatus) {
		this.applicableMaritalStatus = applicableMaritalStatus;
	}

	public void setLeaveBalanceBasedOn(String leaveBalanceBasedOn) {
		this.leaveBalanceBasedOn = leaveBalanceBasedOn;
	}

	public void setApplicableFor(String applicableFor) {
		this.applicableFor = applicableFor;
	}

	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}

	public void setNewJoinee(int newJoinee) {
		this.newJoinee = newJoinee;
	}

	public void setAfterSixMonths(int afterSixMonths) {
		this.afterSixMonths = afterSixMonths;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public void setQuarterDay(boolean quarterDay) {
		this.quarterDay = quarterDay;
	}

	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}

	public void setBeyondPermittedLeave(boolean beyondPermittedLeave) {
		this.beyondPermittedLeave = beyondPermittedLeave;
	}

	public void setConsiderDoj(boolean considerDoj) {
		this.considerDoj = considerDoj;
	}

	public void setRoundOffPermittedLeave(boolean roundOffPermittedLeave) {
		this.roundOffPermittedLeave = roundOffPermittedLeave;
	}

	public void setExcludeHolidays(boolean excludeHolidays) {
		this.excludeHolidays = excludeHolidays;
	}

	public void setExcludeWeekEndsDays(boolean excludeWeekEndsDays) {
		this.excludeWeekEndsDays = excludeWeekEndsDays;
	}

	public void setIncludeAllHolidaysAndWeeklyOffs(boolean includeAllHolidaysAndWeeklyOffs) {
		this.includeAllHolidaysAndWeeklyOffs = includeAllHolidaysAndWeeklyOffs;
	}

	public void setCarryForward(boolean carryForward) {
		this.carryForward = carryForward;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public void setEncashment(boolean encashment) {
		this.encashment = encashment;
	}

	public void setBusinessPeriod(float businessPeriod) {
		this.businessPeriod = businessPeriod;
	}

	public void setDuringProbation(int duringProbation) {
		this.duringProbation = duringProbation;
	}

	public void setJoiningMonthLesserThan(int joiningMonthLesserThan) {
		this.joiningMonthLesserThan = joiningMonthLesserThan;
	}

	public void setJoiningMonthGreaterThan(int joiningMonthGreaterThan) {
		this.joiningMonthGreaterThan = joiningMonthGreaterThan;
	}

	public void setMaxconsiguitiveLeaves(int maxconsiguitiveLeaves) {
		this.maxconsiguitiveLeaves = maxconsiguitiveLeaves;
	}

	public void setConsigutiveDaysExceeds(int consigutiveDaysExceeds) {
		this.consigutiveDaysExceeds = consigutiveDaysExceeds;
	}

	public void setApplicationSubmitBefore(int applicationSubmitBefore) {
		this.applicationSubmitBefore = applicationSubmitBefore;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	public void setResetOn(String resetOn) {
		this.resetOn = resetOn;
	}

	public void setResetMonth(String resetMonth) {
		this.resetMonth = resetMonth;
	}

	public void setMaxAccumulationCount(int maxAccumulationCount) {
		this.maxAccumulationCount = maxAccumulationCount;
	}

	public void setEncashmentMaxLimit(int encashmentMaxLimit) {
		this.encashmentMaxLimit = encashmentMaxLimit;
	}

	public void setProbationPeriod(String probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

}
