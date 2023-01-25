/**
 * 
 */
package com.imaginnovate.assignment.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.assignment.exception.ImaginnovateException;
import com.imaginnovate.assignment.model.request.Employee;
import com.imaginnovate.assignment.entity.EmployeeEntity;
import com.imaginnovate.assignment.service.EmployeeService;
import com.imaginnovate.assignment.util.ImaginnovateUtil;

/**
 * @author Ajay Sarvasiddhi
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public @ResponseBody ResponseEntity<String> saveEmployeeDetails(@RequestBody Employee employee) {
		try {
			if (employee != null) {
				ImaginnovateUtil.validateEmployee(employee);
			}
			EmployeeEntity employeeEntity = new EmployeeEntity();
			BeanUtils.copyProperties(employee, employeeEntity);
			
			employeeEntity.setPhoneNumber(employee.getPhoneNumber().stream().map(Object::toString).collect(Collectors.joining(", ")));
			
			boolean saved = employeeService.saveEmployee(employeeEntity);
			if (!saved)
				throw new Exception("Employee exists with this id");
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Employee details saved successfully. Employee id: " + employee.getId(),
				HttpStatus.OK);
	}

	@GetMapping("/employee/{empId}")
	public @ResponseBody ResponseEntity<Object> getEmployeeTaxDetails(@PathVariable String empId) {
		Optional<EmployeeEntity> employee = Optional.ofNullable(getEmployee(empId));
		try {
			if (employee.isEmpty())
				throw new EntityNotFoundException("User does not exist with user id: " + empId);
		} catch (EntityNotFoundException e) {
			return new ImaginnovateException().handleEntityNotFound(e);
		}

		return new ResponseEntity<Object>(ImaginnovateUtil.prepareEmployeeTaxDetails(employee.get()), HttpStatus.OK);
	}

	private EmployeeEntity getEmployee(String empId) {
		if (employeeService.findEmployeeById(empId) != null) {
			return employeeService.findEmployeeById(empId).isEmpty() ? null : employeeService.findEmployeeById(empId).get();
		}
		return null;
	}

}
