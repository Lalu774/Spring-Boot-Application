package com.employee.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Employee> getAllEmployees() {
		String sql = "select * from employee";
		List<Employee> employees = jdbcTemplate.query(sql , new EmployeeRowMapper());
		return employees;
		
	}
	
	public Employee getEmployee(Long employeeId) {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=?";
		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {employeeId},  new EmployeeRowMapper());
		return employee;
		
	}
	
	public int saveEmployee(Employee employee) {
		String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ROLE, DEPARTMENT) VALUES(?,?,?,?)";
		int rownum = jdbcTemplate.update(sql, new Object[] {employee.getId(), employee.getName(), employee.getRole(), employee.getDepartment()});
		return rownum;
	}
	
	public int updateEmployee(Employee employee) {
		String sql = "UPDATE EMPLOYEE SET EMPLOYEE_NAME=?, EMPLOYEE_ROLE=?, DEPARTMENT=? WHERE EMPLOYEE_ID=?";
		int rownum = jdbcTemplate.update(sql, new Object[] {employee.getName(), employee.getRole(), employee.getDepartment(), employee.getId()});
		return rownum;
	}
	
	public int deleteEmployee(Long employeeId) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID=?";
		int rownum = jdbcTemplate.update(sql, new Object[] {employeeId});
		return rownum;
	}
}
