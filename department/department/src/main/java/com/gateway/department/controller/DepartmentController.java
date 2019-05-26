package com.gateway.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.department.model.Department;
import com.gateway.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	public DepartmentService departmentService;
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		ModelMap map = new ModelMap();
		ResponseEntity<?> response ;
		if(department != null) {
			map.put("Department saved",departmentService.saveDepartment(department));
		  }
		return response = new ResponseEntity<>(map, HttpStatus.OK);
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getDepartment(@PathVariable Long departmentId){
		ModelMap map = new ModelMap();
		ResponseEntity<?> response ;
		if(departmentId != null) {
			map.put("Department detail requested ", departmentService.getDepartment(departmentId));
		  }
		return response = new ResponseEntity<>(map, HttpStatus.OK);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllDepartment(){
		ModelMap map = new ModelMap();
		ResponseEntity<?> response ;
      map.put("All Department detail  ", departmentService.getAllDepartment());
		
		return response = new ResponseEntity<>(map, HttpStatus.OK);
		
	}
	
	

}
