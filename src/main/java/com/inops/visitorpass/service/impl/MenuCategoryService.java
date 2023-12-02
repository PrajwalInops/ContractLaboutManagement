package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.MenuCategoryEntity;
import com.inops.visitorpass.repository.MenuCategoryRepository;
import com.inops.visitorpass.service.IMenuCategory;

import lombok.RequiredArgsConstructor;

@Service("menuCategoryService")
@RequiredArgsConstructor
public class MenuCategoryService implements IMenuCategory {

	private final MenuCategoryRepository menuCategoryRepository;

	@Override
	public Optional<List<MenuCategoryEntity>> findAll() {
		// TODO Auto-generated method stub
		return Optional.of(menuCategoryRepository.findAll());
	}

	@Override
	public Optional<MenuCategoryEntity> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<MenuCategoryEntity> create(MenuCategoryEntity menuCategory) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<MenuCategoryEntity> update(MenuCategoryEntity menuCategory) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(long menuCategoryId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
