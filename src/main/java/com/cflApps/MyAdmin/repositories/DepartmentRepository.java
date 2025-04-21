package com.cflApps.MyAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cflApps.MyAdmin.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	

}
