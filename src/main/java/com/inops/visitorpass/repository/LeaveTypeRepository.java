package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveTypeEntity;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypeEntity, Long> {

}
