package com.employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.employee.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getLong("EMPLOYEE_ID"));
		employee.setName(rs.getString("EMPLOYEE_NAME"));
		employee.setRole(rs.getString("EMPLOYEE_ROLE"));
		employee.setDepartment(rs.getString("DEPARTMENT"));
		return employee;
	}

}
