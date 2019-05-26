package com.GateWayLearning.employeemicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GateWayLearning.employeemicroservice.model.Employee;
import com.GateWayLearning.employeemicroservice.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Optional<Employee> getEmployee(Long employeeId) {
		return employeeRepo.findById(employeeId);
	}
	
	public void deleteEmployee(Long employeeId) {
		employeeRepo.deleteById(employeeId);
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
   
	public List<Employee> getAllEmployee(){
		return employeeRepo.findAll();
	}
}
