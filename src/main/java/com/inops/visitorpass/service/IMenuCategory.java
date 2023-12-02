package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.MenuCategoryEntity;

public interface IMenuCategory {
	Optional<List<MenuCategoryEntity>> findAll();

	Optional<MenuCategoryEntity> findById(long id);

	Optional<MenuCategoryEntity> create(MenuCategoryEntity menuCategory);

	Optional<MenuCategoryEntity> update(MenuCategoryEntity menuCategory);

	void delete(long menuCategoryId);
}
