/**
 * 
 */
package com.imaginnovate.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.imaginnovate.assignment.model.request.Employee;

/**
 * @author Ajay Sarvasiddhi
 *
 */
@Entity
@Table(name = "Employee")
public class EmployeeEntity {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	/*
	@Convert(converter = AppendingPhoneNumberConverter.class)
	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "phone_numbers", joinColumns = {
			@JoinColumn(name = "phone_number_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "PHONE_NUMBER_PROPERTY_FK", foreignKeyDefinition = "FOREIGN KEY (phone_number_id) references employee (id) ON UPDATE NO ACTION ON DELETE CASCADE")) })
	@Column(name = "phone_number")
	private List<String> phoneNumber = new ArrayList<String>();
	*/ 
	//Tried giving the maximum effort for fixing the above snippet but it is still unresolved.
	
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name = "date_of_joining")
	private String dateOfJoining;

	@Column(name = "salary")
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
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
