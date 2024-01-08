package com.inops.visitorpass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Organigram")
public class Organigram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long organigramId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "employeeId", nullable = false, updatable = true)
	private Employee parentEmployee;

	private String type;
	private String department;	
    
    @OneToMany(mappedBy = "organigramChild", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EmployeeNode> employeeNodes;

	public void setOrganigramId(long organigramId) {
		this.organigramId = organigramId;
	}

	public void setParentEmployee(Employee parentEmployee) {
		this.parentEmployee = parentEmployee;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setEmployeeNodes(List<EmployeeNode> employeeNodes) {
		this.employeeNodes = employeeNodes;
	}
    
}
