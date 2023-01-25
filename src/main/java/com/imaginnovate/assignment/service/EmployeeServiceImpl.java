/**
 * 
 */
package com.imaginnovate.assignment.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.assignment.entity.EmployeeEntity;
import com.imaginnovate.assignment.repository.EmployeeRepository;
import com.imaginnovate.assignment.util.AppendingPhoneNumberConverter;

/**
 * @author Ajay Sarvasiddhi
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean saveEmployee(EmployeeEntity employee) {
		Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(employee.getId());
		if(!existingEmployee.isEmpty()) {
			EmployeeEntity employe = existingEmployee.get();
			if(employe != null)
				return false;
		}

		employeeRepository.save(employee);		
		return true;
	}

	public Optional<EmployeeEntity> findEmployeeById(String id) {
		return employeeRepository.findById(id);
	}

}
