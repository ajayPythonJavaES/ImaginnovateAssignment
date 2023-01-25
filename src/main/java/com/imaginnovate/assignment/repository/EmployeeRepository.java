/**
 * 
 */
package com.imaginnovate.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.assignment.entity.EmployeeEntity;

/**
 * @author Ajay Sarvasiddhi
 *
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>{

}
