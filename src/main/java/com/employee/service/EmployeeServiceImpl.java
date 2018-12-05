package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
		
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if(!employees.isEmpty()) {
			return employees;
		}else {
			throw new EmployeeNotFoundException();
		}
	}

	public Employee getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isPresent()) {
			return employee.get();
		}else {
			throw new EmployeeNotFoundException();
		}
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void saveEmployees(List<Employee> employees) {
		employeeRepository.saveAll(employees);
	}
	
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

}