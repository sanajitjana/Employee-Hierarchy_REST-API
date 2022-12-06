package com.eh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eh.exception.EmployeeException;

import com.eh.model.Employee;
import com.eh.model.EmployeeDTO;

import com.eh.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public void checkNull(Integer id) throws EmployeeException {
		if (id == null)
			throw new EmployeeException("Employee id can't be null");
	}

	@Override
	public Employee createNewEmployee(EmployeeDTO employee) throws EmployeeException {

		Integer managerId = employee.getManagerId();
		if (managerId != null) {
			Optional<Employee> employeeOpt = employeeRepo.findById(managerId);
			if (employeeOpt.isEmpty())
				throw new EmployeeException("Manager not exist in database with id " + managerId);
		}

		Employee newEmployee = new Employee();
		newEmployee.setFirstName(employee.getFirstName());
		newEmployee.setLastName(employee.getLastName());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setPhoneNumber(employee.getPhoneNumber());
		newEmployee.setSalary(employee.getSalary());
		newEmployee.setManagerId(employee.getManagerId());

		return employeeRepo.save(newEmployee);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) throws EmployeeException {

		checkNull(employeeId);

		Optional<Employee> employeeOpt = employeeRepo.findById(employeeId);
		if (employeeOpt.isEmpty())
			throw new EmployeeException("Employee not found with id " + employeeId);

		return employeeOpt.get();
	}

	@Override
	public Employee getManagerByEmployeeId(Integer employeeId) throws EmployeeException {

		checkNull(employeeId);

		Optional<Employee> employeeOpt = employeeRepo.findById(employeeId);
		if (employeeOpt.isEmpty())
			throw new EmployeeException("Employee not found with id " + employeeId);

		Integer managerId = employeeOpt.get().getManagerId();

		if (managerId == null)
			throw new EmployeeException("The employee has not any manager");

		Optional<Employee> managerOpt = employeeRepo.findById(managerId);
		if (managerOpt.isEmpty())
			throw new EmployeeException("The employee has not any manager");

		return managerOpt.get();
	}

	@Override
	public List<Employee> getAllManagerByEmployeeId(Integer employeeId) throws EmployeeException {

		checkNull(employeeId);

		List<Employee> managerList = employeeRepo.getAllManagerByEmployeeId(employeeId);
		if (managerList.isEmpty())
			throw new EmployeeException("No manager found!");

		return managerList;
	}

	@Override
	public Employee updateEmployee(EmployeeDTO employee, Integer employeeId) throws EmployeeException {

		checkNull(employeeId);

		Optional<Employee> employeeOpt = employeeRepo.findById(employeeId);
		if (employeeOpt.isEmpty())
			throw new EmployeeException("Employee not found with id " + employeeId);

		Integer managerId = employee.getManagerId();

		if (managerId != null) {
			Optional<Employee> employeeOptt = employeeRepo.findById(managerId);
			if (employeeOptt.isEmpty())
				throw new EmployeeException("Manager not exist in database with id " + managerId);
		}

		Employee updatedEmployee = employeeOpt.get();
		updatedEmployee.setEmpId(employeeId);
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setLastName(employee.getLastName());
		updatedEmployee.setEmail(employee.getEmail());
		updatedEmployee.setPhoneNumber(employee.getPhoneNumber());
		updatedEmployee.setSalary(employee.getSalary());
		updatedEmployee.setManagerId(employee.getManagerId());

		return employeeRepo.save(updatedEmployee);
	}

	@Override
	public Employee deleteEmployeeById(Integer employeeId) throws EmployeeException {

		checkNull(employeeId);

		Optional<Employee> employeeOpt = employeeRepo.findById(employeeId);
		if (employeeOpt.isEmpty())
			throw new EmployeeException("Employee not found with id " + employeeId);

		Employee deletedEmployee = employeeOpt.get();
		employeeRepo.delete(deletedEmployee);

		return deletedEmployee;
	}

	@Override
	public List<Employee> getAllEmployee() throws EmployeeException {

		List<Employee> employeeList = employeeRepo.findAll();
		if (employeeList.isEmpty())
			throw new EmployeeException("No employee found!");

		return employeeList;
	}
}
