/**
 * 
 */
package com.imaginnovate.assignment.service;

import java.util.Optional;

import com.imaginnovate.assignment.entity.EmployeeEntity;

/**
 * @author Ajay Sarvasiddhi
 *
 */
public interface EmployeeService {

	public boolean saveEmployee(EmployeeEntity employee);
	
	public Optional<EmployeeEntity> findEmployeeById(String empId);
	
}
