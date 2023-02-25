package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.MenuItemEntity;

public interface IMenuItem {

	Optional<List<MenuItemEntity>> findAll();

	Optional<MenuItemEntity> findById(long id);

	Optional<MenuItemEntity> create(MenuItemEntity menuItem);

	Optional<MenuItemEntity> update(MenuItemEntity menuItem);

	void delete(long leaveTypeId);
}
