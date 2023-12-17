package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveApplicationType;

public interface LeaveApplicationTypeRepository extends JpaRepository<LeaveApplicationType, Long> {

}
