package com.inops.visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inops.visitorpass.entity.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {

}
