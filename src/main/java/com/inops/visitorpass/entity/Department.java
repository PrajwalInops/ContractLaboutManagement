package com.inops.visitorpass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbldepartments")
public class Department {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "deptid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "department")
	private String departmentName;

}
