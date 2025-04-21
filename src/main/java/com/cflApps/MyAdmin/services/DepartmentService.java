package com.cflApps.MyAdmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cflApps.MyAdmin.dtos.DepartmentDTO;
import com.cflApps.MyAdmin.entities.Department;
import com.cflApps.MyAdmin.exceptions.MyAdminServiceException;
import com.cflApps.MyAdmin.repositories.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentService {

	private final DepartmentRepository repo;

	private List<Department> departmentList;
	
	@Autowired
	public DepartmentService(DepartmentRepository repo) {
		this.repo = repo;
		this.departmentList = repo.findAll();
	}

	public DepartmentDTO save(DepartmentDTO newDepartment) {
		try {
			Department entity = this.repo.save(new Department(newDepartment));
			return new DepartmentDTO(entity);
		} catch (Exception e) {
			String message = String.format("Error saving  departament %s: %s",newDepartment.getName(), e.getMessage());
			log.error(message);
			throw new MyAdminServiceException(message);
		}
	}

	public List<DepartmentDTO> getAll() {
		try {
			return this.repo.findAll().stream().map(DepartmentDTO::new).toList();
		} catch (Exception e) {
			String message = String.format("Error fetching all departaments: %s", e.getMessage());
			log.error(message);
			throw new MyAdminServiceException(message);
		}
	}

	public void update(DepartmentDTO depDTO) {
		try {
			Department depEntity = this.repo.findById(depDTO.getId()).orElseThrow(() -> new EntityNotFoundException(
					String.format("Department Not Found with id: %d", depDTO.getId())));
			
			depEntity.updateValues(depDTO);
			this.repo.save(depEntity);

		} catch (EntityNotFoundException nfe) {
			log.error(nfe.getMessage());
			throw nfe;
		} catch (Exception e) {
			String message = String.format("Error updating Departament: %s",e.getMessage());
			log.error(message);
			throw new MyAdminServiceException(message);
		}
	}

	public void deleteById(Long id) {
		try {
			this.repo.deleteById(id);			
		} catch (Exception e) {
			String message = String.format("Error deleting Departament with ID[%d]: %s", id, e.getMessage());
			log.error(message);
			throw new MyAdminServiceException(message);
		}
		
	}
	
	public Department getEntityById(Long id) {
		this.departmentList = repo.findAll();

		return this.departmentList.stream().filter(dep -> dep.getId().equals(id)).findFirst()
				.orElseThrow(() -> new EntityNotFoundException(String.format("Department not found with id: %d", id)));
	}

}
