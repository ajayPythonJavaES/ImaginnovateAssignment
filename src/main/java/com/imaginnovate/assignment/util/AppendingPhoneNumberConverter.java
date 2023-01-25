/**
 * 
 */
package com.imaginnovate.assignment.util;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Ajay Sarvasiddhi
 *
 */
@Converter(autoApply = true)
public class AppendingPhoneNumberConverter implements AttributeConverter<List<String>, String> {

	private static final String SEPARATOR = ", ";
	
	@Override
	public String convertToDatabaseColumn(List<String> phoneNumbers) {
		StringBuilder stringBuilder = new StringBuilder();
		if(phoneNumbers != null && phoneNumbers.size() > 0) {
			for(String phoneNumber : phoneNumbers)
				stringBuilder.append(phoneNumber).append(SEPARATOR);
			stringBuilder.replace(stringBuilder.toString().length(), stringBuilder.toString().length() - 1, "");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public List<String> convertToEntityAttribute(String phoneNumbers) {
		if(phoneNumbers == null || phoneNumbers.isEmpty())
			return null;
		return Arrays.asList(phoneNumbers.split(SEPARATOR));
	}
	
}
