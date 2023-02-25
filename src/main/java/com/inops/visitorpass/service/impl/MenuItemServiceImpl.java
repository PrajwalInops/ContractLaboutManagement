package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.MenuItemEntity;
import com.inops.visitorpass.repository.MenuItemRepository;
import com.inops.visitorpass.service.IMenuItem;

@Service("menuItemService")
public class MenuItemServiceImpl implements IMenuItem {

	private MenuItemRepository menuItemRepository;
			
	public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
		super();
		this.menuItemRepository = menuItemRepository;
	}

	@Override
	public Optional<List<MenuItemEntity>> findAll() {
		
		return Optional.of(menuItemRepository.findAll());
	}

	@Override
	public Optional<MenuItemEntity> findById(long id) {
		return menuItemRepository.findById(id);
	}

	@Override
	public Optional<MenuItemEntity> create(MenuItemEntity menuItem) {
		return Optional.empty();
	}

	@Override
	public Optional<MenuItemEntity> update(MenuItemEntity menuItem) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(long leaveTypeId) {
		// TODO Auto-generated method stub

	}

}
