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
import lombok.experimental.Accessors;

@Getter
//@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbldesignations")
public class Designation {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "desigid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "designation")
	private String designationName;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
}
