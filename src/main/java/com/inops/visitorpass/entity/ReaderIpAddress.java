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
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblip")
public class ReaderIpAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recid")
    private Long id;
	
	@Column(name = "ip_addr")
	private String ipAddress;
	
	@Column(name = "macid")
	private String macId;
	
	@Column(name = "devslno")
	private long deviceSlno;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "locid")
	private long locationId;

}
