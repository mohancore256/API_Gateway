package com.GateWayLearning.employeemicroservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.GateWayLearning.employeemicroservice.model.Department;
import com.GateWayLearning.employeemicroservice.model.Employee;
import com.GateWayLearning.employeemicroservice.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Controller", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	@PostMapping(value = "/save")
	@ApiOperation("Creating the Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		ModelMap map = new ModelMap();
		ResponseEntity<?> response;
		Employee employeeResponse = employeeService.saveEmployee(employee);
		if (employeeResponse != null && employeeResponse.getEmployeeId() > 0) {
			map.put("Employee Saved", employeeResponse);
			response = new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("Employee Not Saved", "Bad Request");
			response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping(value = "/get/{employeeId}")
	@ApiOperation("Get the Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	public ResponseEntity<?> readEmployee(@PathVariable Long employeeId) {
		ModelMap map = new ModelMap();
		ResponseEntity<?> response;
		Optional<Employee> employeeResponse = employeeService.getEmployee(employeeId);
		if (null != employeeResponse) {
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<Map<String, List<Department>>> deptResponseEntity = restTemplate.exchange(
					"http://localhost:8083/department/department/getAll", HttpMethod.GET, null,
					new ParameterizedTypeReference<Map<String, List<Department>>>() {}
					);
			if (null != deptResponseEntity) {
				Map<String, List<Department>> deptResponseMap = deptResponseEntity.getBody();
				if (null != deptResponseMap && deptResponseMap.containsKey("All Department detail  ")) {
					List<Department> deptList = deptResponseMap.get("All Department detail  ");
					employeeResponse.get().setDepartments(deptList);
				}
			}
		}
		if (employeeResponse != null) {
			map.put("Employee Detail Requested", employeeResponse);
			response = new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("Employee Not Saved", "Bad Request");
			response = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping(value = "/greet")
	public String getGreeting() {
		return "Hi Mohan";
	}

	@DeleteMapping(value = "/delete")
	@ApiOperation("Delete the Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
		ModelMap map = new ModelMap();
		ResponseEntity<?> response;
		employeeService.deleteEmployee(employeeId);
		map.put("Employee Detail Deleted ", " " + employeeId + " ");
		return response = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/update")
	@ApiOperation("Update the Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		ModelMap map = new ModelMap();
		ResponseEntity<?> response;
		Employee employeeResponse = employeeService.updateEmployee(employee);
		if (employeeResponse != null && employeeResponse.getEmployeeId() > 0) {
			map.put("Employee updated", employeeResponse);
			response = new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("Employee Not updated", "Bad Request");
			response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllEmployee() {
		ModelMap map = new ModelMap();
		ResponseEntity<?> response;
		map.put("All emp details ", employeeService.getAllEmployee());
		response = new ResponseEntity<>(map, HttpStatus.OK);
		return response;
	}
}
