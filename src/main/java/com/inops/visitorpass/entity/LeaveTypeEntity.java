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
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "LeaveType")
public class LeaveTypeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long leaveTypeId;
    
    private String leaveName;    
    private String leaveCode;
    private String leaveType;
    private String leaveUnits;
    private String leaveBalanceBasedOn;
    private Date validFrom;
    private Date validTo;
    private String leaveDescription;
    
    
    private String applicableFor;
    private String applicableRole;
    private String applicableLocation;
    private String applicableGender;
    private String applicableMaritalStatus;
    
     
    
    private boolean quarterDay;
    private boolean halfDay;
    private boolean beyondPermittedLeave;
    private boolean roundOffPermittedLeave;
    private boolean excludeHolidays;
    private boolean excludeWeekEndsDays;
    private boolean includeAllHolidaysAndWeeklyOffs;
    private float maxConsecutiveDays;
    private int holidayWeekendConsicutive;
    private int applicationSubmitBefore;
    
    
    private int effectiveAfter;
    private String entitlementInterval;
    private String effectiveFrom;
    
    private boolean accrual; 
    private String accrualType;
    private String accrualOn;
    private String accrualMonth;
    private float noOfDays;
     
    private boolean reset; 
    private String resetType;
    private String resetOn;
    private String resetMonth;
        
    private String carryForwardType;
    private int unit;
    private String carryForwardScale;
    private int carryForwardMaxLimit;
    private int expiresIn;
    private String carryForwardInterval;
    
    private int encashment;
    private String encashmentScale;
    private int encashmentMaxLimit;
    private String prorateAccrual;

}
