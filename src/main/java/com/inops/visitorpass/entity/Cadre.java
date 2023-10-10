package com.inops.visitorpass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Tblcadres")
public class Cadre {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cadreid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cadreId;

	@Column(name = "cadre")
	private String cadre;
	
	@Column(name = "wagestperiod")
	private int wagePeriod;
	
	@Column(name = "lateinflag")
	private boolean lateInFlag;
	
	@Column(name = "earlyoutflag")
	private boolean earlyOutFlag;
	
	@Column(name = "otflag")
	private boolean otFlag;
	
	@Column(name = "coffflag")
	private boolean compOffFlag;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}

	public void setCadre(String cadre) {
		this.cadre = cadre;
	}

	public void setWagePeriod(int wagePeriod) {
		this.wagePeriod = wagePeriod;
	}

	public void setLateInFlag(boolean lateInFlag) {
		this.lateInFlag = lateInFlag;
	}

	public void setEarlyOutFlag(boolean earlyOutFlag) {
		this.earlyOutFlag = earlyOutFlag;
	}

	public void setOtFlag(boolean otFlag) {
		this.otFlag = otFlag;
	}

	public void setCompOffFlag(boolean compOffFlag) {
		this.compOffFlag = compOffFlag;
	}

}
