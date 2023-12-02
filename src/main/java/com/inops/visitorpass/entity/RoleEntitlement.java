package com.inops.visitorpass.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	private String entitlementName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Entitlement_Menu_Item", joinColumns = @JoinColumn(name = "Menu_Item_Id"), inverseJoinColumns = @JoinColumn(name = "entitlement_Role_Id"))
	private Set<MenuItemEntity> menuItem = new HashSet<>();

}
