package com.GateWayLearning.employeemicroservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "EMPLOYEE_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private Long employeeId;
		
		@Column
		private String firstName;
		
		@Column
		private Integer age;
		
		@Transient
		private List<Department> departments;

		public Long getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public List<Department> getDepartments() {
			return departments;
		}

		public void setDepartments(List<Department> departments) {
			this.departments = departments;
		}
		
		


		
		
}
