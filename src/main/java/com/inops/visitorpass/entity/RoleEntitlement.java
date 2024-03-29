package com.inops.visitorpass.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RoleEntitlement")
public class RoleEntitlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long entitlementRoleId;

	private String category;
	private String menuItem;
	
	@ManyToOne
	@JoinColumn(name = "entitlementId", nullable = false)	
	private Entitlement entitlement;
	

}
