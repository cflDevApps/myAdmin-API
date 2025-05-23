package com.cflApps.MyAdmin.dtos;

import com.cflApps.MyAdmin.entities.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDTO {
	
	private Long id;
	private String name;
	private String manager;
	
	
	public DepartmentDTO(Department entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.manager = entity.getManager();
	}
	

}
