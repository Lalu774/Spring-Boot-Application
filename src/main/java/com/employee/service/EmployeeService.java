package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.entity.Employee;

@Service
public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(Long employeeId);
	
	public int saveEmployee(Employee employee);
	
	public void deleteEmployee(Long employeeId);
	
	public int updateEmployee(Employee employee);
	
	public Employee isEmployeeExists(Long employeeId);
}
