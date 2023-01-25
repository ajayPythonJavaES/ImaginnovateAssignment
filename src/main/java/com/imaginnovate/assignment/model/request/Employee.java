/**
 * 
 */
package com.imaginnovate.assignment.model.request;

import java.util.List;

/**
 * @author Ajay Sarvasiddhi
 *
 */
public class Employee {

	private String id;
	
	private String firstName;
	private String lastName;
	private String email;
	private List<String> phoneNumber;
	private String dateOfJoining;
	private double salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Employee))
			return false;
		Employee employee = (Employee) o;
		return employee.getId().equals(this.getId()) && employee.getFirstName().equals(this.getFirstName())
				&& employee.getLastName().equals(this.getLastName()) && employee.getEmail().equals(this.getEmail())
				&& employee.getDateOfJoining().equals(this.getDateOfJoining());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + email.length() + firstName.length() + lastName.length() + dateOfJoining.length();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		return result;
	}
	
}
