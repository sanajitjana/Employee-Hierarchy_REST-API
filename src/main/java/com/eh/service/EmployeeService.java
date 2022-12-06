package com.eh.service;

import java.util.List;

import com.eh.exception.EmployeeException;

import com.eh.model.Employee;
import com.eh.model.EmployeeDTO;

public interface EmployeeService {

	public Employee createNewEmployee(EmployeeDTO employee) throws EmployeeException;

	public Employee getEmployeeById(Integer employeeId) throws EmployeeException;

	public Employee getManagerByEmployeeId(Integer employeeId) throws EmployeeException;

	public List<Employee> getAllManagerByEmployeeId(Integer employeeId) throws EmployeeException;

	public Employee updateEmployee(EmployeeDTO post, Integer employeeId) throws EmployeeException;

	public Employee deleteEmployeeById(Integer employeeId) throws EmployeeException;

	public List<Employee> getAllEmployee() throws EmployeeException;
}
