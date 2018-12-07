package com.employee.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Employee> getAllEmployees() {
		String sql = "SELECT ID, NAME, ROLE, DEPARTMENT FROM EMPLOYEE";
		List<Employee> employees = jdbcTemplate.query(sql , new EmployeeRowMapper());
		return employees;
		
	}
	
	public Employee getEmployee(Long employeeId) {
		String sql = "SELECT ID, NAME, ROLE, DEPARTMENT FROM EMPLOYEE WHERE ID=?";
		Employee employee = null;
		try{
			employee = jdbcTemplate.queryForObject(sql, new Object[] {employeeId},  new EmployeeRowMapper());
		}catch(EmptyResultDataAccessException e) {
		}
		if(employee != null) {
			return employee;
		}else {
			throw new EmployeeNotFoundException(); 
		}
		
	}
	
	public Employee isEmployeeExists(Long employeeId) {
		String sql = "SELECT ID, NAME, ROLE, DEPARTMENT FROM EMPLOYEE WHERE ID=?";
		Employee employee = null;
		try{
			employee = jdbcTemplate.queryForObject(sql, new Object[] {employeeId},  new EmployeeRowMapper());
		}catch(EmptyResultDataAccessException e) {
			return employee;
		}
		return employee;	}
	
	public int saveEmployee(Employee employee) {
		String sql = "INSERT INTO EMPLOYEE (ID, NAME, ROLE, DEPARTMENT)" + "VALUES(?,?,?,?)";
		int rownum = jdbcTemplate.update(sql, new Object[] {employee.getId(), employee.getName(), employee.getRole(), employee.getDepartment()});
		return rownum;
	}
	
	public int updateEmployee(Employee employee) {
		String sql = "UPDATE EMPLOYEE" + " SET NAME=?, ROLE=?, DEPARTMENT=?" + "WHERE ID=?";
		int rownum = jdbcTemplate.update(sql, new Object[] {employee.getName(), employee.getRole(), employee.getDepartment(), employee.getId()});
		return rownum;
	}
	
	public int deleteEmployee(Long employeeId) {
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
		int rownum = jdbcTemplate.update(sql, new Object[] {employeeId});
		return rownum;
	}
}
