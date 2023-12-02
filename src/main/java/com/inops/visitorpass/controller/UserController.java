package com.inops.visitorpass.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.domain.Entitlements;
import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.entity.MenuItemEntity;
import com.inops.visitorpass.entity.RoleEntitlement;
import com.inops.visitorpass.service.IEntitlement;
import com.inops.visitorpass.service.IMenuCategory;
import com.inops.visitorpass.service.IUserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("userController")
@Scope("session")
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;
	private final IMenuCategory menuCategoryService;
	private final IEntitlement entitlementService;

	private String emailId;
	private String oldPassword;
	private String newPassword;

	private String[] selectedMenuCategories;
	private List<SelectItem> appMenuCategories;

	private Entitlements roleEntitlement;
	private List<Entitlements> roleEntitlements;
	private List<Entitlements> selectedRoleEntitlements;

	private List<RoleEntitlement> dbRoleEntitlements;

	@PostConstruct
	public void init() {
		selectedMenuCategories = new String[100];
		rollMenue();
		getRoles();
	}

	public void openNew() {
		this.roleEntitlement = new Entitlements();
	}

	public void resetPassword() {
		try {
			boolean isReset = userService.resetPassword(new ResetPassword(emailId, oldPassword, newPassword));
			if (isReset)
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Password changed successfully for: " + emailId);
			else
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Old Password not matching for : " + emailId);
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Failed to reset password for : " + emailId);
			log.error("exception at the time of resetPassword {}", e);
		}
		cleanUp();
	}

	private void rollMenue() {
		appMenuCategories = new ArrayList<>();
		menuCategoryService.findAll().get().forEach(menuCategori -> {
			SelectItemGroup categories = new SelectItemGroup(menuCategori.getLabel());
			List<SelectItem> arr = menuCategori.getMenuItem().stream()
					.map(menu -> new SelectItem(menu.getLabel(), menu.getLabel())).collect(Collectors.toList());
			SelectItem[] objectArray = arr.toArray(new SelectItem[0]);
			categories.setSelectItems(objectArray);
			appMenuCategories.add(categories);
		});
	}

	public void saveRole() {
		try {

			Set<MenuItemEntity> menuEntity = new HashSet<>();
			menuCategoryService.findAll().get().forEach(menuItem -> {
				menuItem.getMenuItem().forEach(items -> {
					String menuItemValue = Arrays.stream(roleEntitlement.getSelectedMenuCategories())
							.filter(role -> role.equals(items.getLabel())).findAny().orElse(null);
					if (menuItemValue != null) {
						menuEntity.add(items);
					}
				});
			});
			RoleEntitlement role = new RoleEntitlement(roleEntitlement.getEntitlementId(),
					roleEntitlement.getEntitlementName(), menuEntity);

			RoleEntitlement roleEnt = entitlementService.create(role).get();
			if (roleEntitlement.getEntitlementId() == 0l) {
				roleEntitlement.setEntitlementId(roleEnt.getEntitlementRoleId());
				this.roleEntitlements.add(roleEntitlement);
			} else {
				Entitlements entitlement = roleEntitlements.stream()
						.filter(ent -> ent.getEntitlementId() == roleEntitlement.getEntitlementId()).findAny()
						.orElse(null);
				entitlement.setEntitlementName(roleEntitlement.getEntitlementName());
				entitlement.setSelectedMenuCategories(roleEntitlement.getSelectedMenuCategories());
			}
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Entitlement Added successfully");

		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", e.getMessage());
		}
	}

	private void getRoles() {
		dbRoleEntitlements = entitlementService.findAll().get();
		roleEntitlements = dbRoleEntitlements
				.stream().map(
						entitel -> new Entitlements(entitel.getEntitlementRoleId(), entitel.getEntitlementName(),
								entitel.getMenuItem().stream().map(MenuItemEntity::getLabel)
										.collect(Collectors.toList()).toArray(new String[0])))
				.collect(Collectors.toList());
	}

	public void deleteRole() {

		entitlementService.delete(roleEntitlement.getEntitlementId());
		this.roleEntitlements.remove(this.roleEntitlement);
		if (this.selectedRoleEntitlements != null) {
			this.selectedRoleEntitlements.remove(this.roleEntitlement);
		}
		this.roleEntitlement = null;
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Entitlemnt deleted successfully");
	}

	public void deleteRoles() {

		entitlementService.deleteAll(
				selectedRoleEntitlements.stream().map(ent -> ent.getEntitlementId()).collect(Collectors.toList()));
		this.roleEntitlements.removeAll(this.selectedRoleEntitlements);
		this.selectedRoleEntitlements = null;
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Entitlemnt deleted successfully");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteRolesButtonMessage() {
		if (hasSelectedRoles()) {
			int size = this.selectedRoleEntitlements.size();
			return size > 1 ? size + " Entitlemnt selected" : "1 Entitlemnt selected";
		}

		return "Delete";
	}

	public boolean hasSelectedRoles() {
		return this.selectedRoleEntitlements != null && !this.selectedRoleEntitlements.isEmpty();
	}

	private void cleanUp() {
		setEmailId(null);
		setNewPassword(null);
		setOldPassword(null);
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
