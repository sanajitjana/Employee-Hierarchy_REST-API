package com.eh.model;

import lombok.Data;

@Data
public class EmployeeDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Double salary;
	private Integer managerId;
}
