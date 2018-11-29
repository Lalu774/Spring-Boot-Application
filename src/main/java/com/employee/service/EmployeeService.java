package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(Long employeeId);
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(Long employeeId);
	
	public void updateEmployee(Employee employee);
}
