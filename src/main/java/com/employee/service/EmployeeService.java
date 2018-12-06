package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(Long employeeId);
	
	public int saveEmployee(Employee employee);
	
	public void deleteEmployee(Long employeeId);
	
	public int updateEmployee(Employee employee);
}
