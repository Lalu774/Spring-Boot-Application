package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeRepository;
		
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeRepository.getAllEmployees();
		if(!employees.isEmpty()) {
			return employees;
		}else {
			throw new EmployeeNotFoundException();
		}
	}

	public Employee getEmployee(Long employeeId) {
		Employee employee = employeRepository.getEmployee(employeeId);
		if (!employee.getName().isEmpty()) {
			return employee;
		}else {
			throw new EmployeeNotFoundException();
		}
	}
	
	public int saveEmployee(Employee employee) {
		int result = employeRepository.saveEmployee(employee);
		return result;
	}
	
	public void deleteEmployee(Long employeeId) {
		employeRepository.deleteEmployee(employeeId);
	}

	public int updateEmployee(Employee employee) {
		int result = employeRepository.updateEmployee(employee);
		return result;
	}

}