package com.gateway.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gateway.department.model.Department;
import com.gateway.department.repository.DepartmentRepos;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepos departmentRepos;
	
	public Department saveDepartment(Department department) {
		return departmentRepos.save(department);
	}
	
	public Optional<Department> getDepartment(Long departmentId) {
		return departmentRepos.findById(departmentId);
	}
	public List<Department> getAllDepartment(){
		return departmentRepos.findAll();
	}

}
