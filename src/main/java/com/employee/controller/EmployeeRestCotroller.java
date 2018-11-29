package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeRestCotroller {

	@Autowired
	private EmployeeService employeeService; 
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/api/employees")
	public List<Employee> getAllEmployees(){
		List<Employee> employees = employeeService.getAllEmployees();
		return employees;
	}
	
	@GetMapping("api/employees/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployee(employeeId);
	}
	
	@PostMapping("api/employees")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return new ResponseEntity<>("Employee detail created successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("api/employees/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>("Employee detail deleted successfully", HttpStatus.OK);
	}
	
	@PutMapping("api/employees/{employeeId}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") Long employeeId) {
		if((employee.getId() != null) && (employee.getId() == employeeId)) {
			Employee emp = employeeService.getEmployee(employeeId);
			if(emp != null) {
				employeeService.updateEmployee(employee);
				return new ResponseEntity<>("Employee detail deleted successfully", HttpStatus.OK);
			}else {
				throw new EmployeeNotFoundException();
			}
		}else {
			return new ResponseEntity<>("Invalid Employee Id in Request", HttpStatus.BAD_REQUEST);
		}
	}
}
