package com.gateway.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gateway.department.model.Department;

@Repository
public interface DepartmentRepos  extends JpaRepository<Department, Long>{
	

}
