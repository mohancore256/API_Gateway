package com.GateWayLearning.employeemicroservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GateWayLearning.employeemicroservice.model.Employee;



@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>  {
	
	@Query(nativeQuery=true , value= "select * from employee_table where employee_id regexp ?1 OR first_name regexp  ?2")
	public Optional<Employee> getEmployeeByNameOrId(String employeeId ,String firstName);
	
}
