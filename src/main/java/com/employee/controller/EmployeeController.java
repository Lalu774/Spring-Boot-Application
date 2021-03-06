package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping("v1/employees")
	public ModelAndView getAllEmployees(){
		List<Employee> employees = employeeService.getAllEmployees();
		//return new ResponseEntity<List<Employee>>(employees , HttpStatus.OK);
		ModelAndView model = new ModelAndView();
		model.addObject("employees", employees);
		model.setViewName("index");
		return model;
	}
	
	@GetMapping("v1/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") Long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployee(employeeId), HttpStatus.OK);
	}
	
	@PostMapping("v1/employees")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		
		ModelAndView model = new ModelAndView("redirect:/api/v1/employees");
		
		Employee emp = employeeService.isEmployeeExists(employee.getId());
		if(emp == null) {
			int result = employeeService.saveEmployee(employee);
			if(result >= 1) {
				model.setStatus(HttpStatus.OK);;
			}else {
				model.addObject("errorMessage", "Error occured while creating employee detail");
			}
		}else {
			model.addObject("errorMessage", "Employee already exists");
		}
		return model;
	}
	
	/*@PostMapping("v1/employees")
	public ResponseEntity<String> saveEmployees(@RequestBody List<Employee> employees) {
		employeeService.saveEmployees(employees);
		return new ResponseEntity<String>("Employee detail created successfully", HttpStatus.CREATED);
	}*/
	
	@DeleteMapping("v1/employees/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		Employee emp = employeeService.getEmployee(employeeId);
		if(emp != null) {
			employeeService.deleteEmployee(employeeId);
			return new ResponseEntity<String>("Employee detail deleted successfully", HttpStatus.OK);
		}else {
			throw new EmployeeNotFoundException();
		}
	}
	
	@PutMapping("v1/employees/{employeeId}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") Long employeeId) {
		
			Employee emp = employeeService.getEmployee(employeeId);
			if(emp != null) {
				int result = employeeService.updateEmployee(employee);
				if(result >= 1) {
					return new ResponseEntity<String>("Employee detail updated successfully", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Employee detail is not updated successfully", HttpStatus.BAD_REQUEST);
				}
			}else {
				throw new EmployeeNotFoundException();
			}
		
	}
}
