/**
 * 
 */
package com.imaginnovate.assignment.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.imaginnovate.assignment.entity.EmployeeEntity;
import com.imaginnovate.assignment.exception.ValidationExceptions;
import com.imaginnovate.assignment.model.request.Employee;
import com.imaginnovate.assignment.model.response.EmployeeTaxDetails;

/**
 * @author Ajay Sarvasiddhi
 *
 */
public class ImaginnovateUtil {

	public static void validateEmployee(Employee employee) throws ValidationExceptions {
		//boolean validate = validateEmailAddress(employee.getEmail()) && validatePhoneNumber(employee.getPhoneNumber());
		
		if(!validateEmailAddress(employee.getEmail())) 
			throw new ValidationExceptions("Not a valid mail address");					
		else if(!validatePhoneNumber(employee.getPhoneNumber()))
			throw new ValidationExceptions("Not a valid Indian contact number");
		
		try {
			convertStringToLocalDateTime(employee.getDateOfJoining());
		} catch (DateTimeParseException ex) {
			throw new ValidationExceptions("Invalid date or date format. Please use dd-MMM-yyyy format");
		}
	}

	private static boolean validateEmailAddress(String email) {
		String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static boolean validatePhoneNumber(List<String> mobileNumbers) {
		return mobileNumbers.stream().anyMatch(number -> number.length() == 10);
		//return mobileNumber.length() == 10;
	}

	public static LocalDate convertStringToLocalDateTime(String date) throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate parsedDate = LocalDate.parse(date, formatter);
		return parsedDate;
	}

	public static EmployeeTaxDetails prepareEmployeeTaxDetails(EmployeeEntity employee) {
		double salary = employee.getSalary();
		LocalDate employeeJoiningDate = convertStringToLocalDateTime(employee.getDateOfJoining());
		double salaryPerDay = salary / 365;
		int dayJoined = employeeJoiningDate.getDayOfYear();

		int yearJoined = employeeJoiningDate.getYear();
		
		int workingDays = 0;
		
		if(yearJoined % 4 == 0 || yearJoined % 100 == 0 || yearJoined % 400 == 0)
			workingDays = (365 - dayJoined) + 91;
		else
			workingDays = (365 - dayJoined) + 90;
		
		double perAnnumSalary = workingDays  * salaryPerDay;
		
		double taxToBeDeducted = 0;
		
		if(perAnnumSalary > 250000 && perAnnumSalary <= 500000)
			taxToBeDeducted += 12500;
		if(perAnnumSalary > 500000 && perAnnumSalary <= 1000000)
			taxToBeDeducted += 25000;
		if(perAnnumSalary > 1000000) {
			taxToBeDeducted +=  (((perAnnumSalary - 1000000)*20)/100);
		}
			
		double cess = 0;
		
		if(perAnnumSalary < 2500000)
			cess = (perAnnumSalary * 4)/100;
		else 
			cess = (perAnnumSalary * 6)/100;
		
		EmployeeTaxDetails employeeTaxDetails = new EmployeeTaxDetails();
		employeeTaxDetails.setFirstName(employee.getFirstName());
		employeeTaxDetails.setId(employee.getId());
		employeeTaxDetails.setLastName(employee.getLastName());
		employeeTaxDetails.setYearlySalary(perAnnumSalary);
		employeeTaxDetails.setTaxAmount(taxToBeDeducted);
		employeeTaxDetails.setCessAmount(cess);
		
		
		return employeeTaxDetails;
	}

}
