package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.MenuItemEntity;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long>{

}
