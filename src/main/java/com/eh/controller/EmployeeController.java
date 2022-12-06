package com.eh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.exception.EmployeeException;

import com.eh.model.Employee;
import com.eh.model.EmployeeDTO;

import com.eh.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("employees")
	public ResponseEntity<Employee> createNewEmployee(@RequestBody EmployeeDTO employee) throws EmployeeException {
		return new ResponseEntity<Employee>(employeeService.createNewEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/get-employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Integer employeeId)
			throws EmployeeException {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	@GetMapping("/get-employee-manager/{employeeId}")
	public ResponseEntity<Employee> getManagerByEmployeeId(@PathVariable("employeeId") Integer employeeId)
			throws EmployeeException {
		return new ResponseEntity<Employee>(employeeService.getManagerByEmployeeId(employeeId), HttpStatus.OK);
	}

	@GetMapping("/get-all-reportees/{employeeId}")
	public ResponseEntity<List<Employee>> getAllManagerByEmployeeId(@PathVariable("employeeId") Integer employeeId)
			throws EmployeeException {
		return new ResponseEntity<List<Employee>>(employeeService.getAllManagerByEmployeeId(employeeId), HttpStatus.OK);
	}

	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Integer employeeId,
			@RequestBody EmployeeDTO employee) throws EmployeeException {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("employeeId") Integer employeeId)
			throws EmployeeException {
		return new ResponseEntity<Employee>(employeeService.deleteEmployeeById(employeeId), HttpStatus.OK);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee() throws EmployeeException {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.OK);
	}

}
