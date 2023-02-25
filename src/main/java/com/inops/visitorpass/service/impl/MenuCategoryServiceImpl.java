package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.MenuCategoryEntity;
import com.inops.visitorpass.repository.MenuCategoryRepository;
import com.inops.visitorpass.service.IMenuCategory;

@Service("menuCategoryService")
public class MenuCategoryServiceImpl implements IMenuCategory{
	
	private MenuCategoryRepository menuCategoryRepository;
		
	public MenuCategoryServiceImpl(MenuCategoryRepository menuCategoryRepository) {
		super();
		this.menuCategoryRepository = menuCategoryRepository;
	}

	@Override
	public Optional<List<MenuCategoryEntity>> findAll() {
		return Optional.of(menuCategoryRepository.findAll());
	}

	@Override
	public Optional<MenuCategoryEntity> findById(long id) {
		
		return menuCategoryRepository.findById(id);
	}

	@Override
	public Optional<MenuCategoryEntity> create(MenuCategoryEntity menuCategory) {
	
		return Optional.of(menuCategoryRepository.save(menuCategory));
	}

	@Override
	public Optional<MenuCategoryEntity> update(MenuCategoryEntity menuCategory) {
		return Optional.of(menuCategoryRepository.save(menuCategory));
	}

	@Override
	public void delete(long menuCategoryId) {
		Optional<MenuCategoryEntity> menuCategory = menuCategoryRepository.findById(menuCategoryId);
		menuCategoryRepository.delete(menuCategory.get());
	}

}
