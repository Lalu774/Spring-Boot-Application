package com.employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.employee.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getLong("id"));
		employee.setName(rs.getString("name"));
		employee.setRole(rs.getString("role"));
		employee.setDepartment(rs.getString("department"));
		return employee;
	}

}
