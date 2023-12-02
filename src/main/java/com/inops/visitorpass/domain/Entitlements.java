package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entitlements {

	private long entitlementId;
	private String entitlementName;
	private String[] selectedMenuCategories;
	
	public long getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(long entitlementId) {
		this.entitlementId = entitlementId;
	}
	public String getEntitlementName() {
		return entitlementName;
	}
	public void setEntitlementName(String entitlementName) {
		this.entitlementName = entitlementName;
	}
	public String[] getSelectedMenuCategories() {
		return selectedMenuCategories;
	}
	public void setSelectedMenuCategories(String[] selectedMenuCategories) {
		this.selectedMenuCategories = selectedMenuCategories;
	}
	
	
}
