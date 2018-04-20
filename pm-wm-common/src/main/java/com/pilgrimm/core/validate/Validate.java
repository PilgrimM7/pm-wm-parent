package com.pilgrimm.core.validate;

/**
 * @author liwei
 */
public interface Validate {
	
	void date(String fieldName);
	
	void datetime(String fieldName);
	
	void integer(String fieldName);

	void decimal(String fieldName, int fraction);
	
	void digits(String fieldName, int integer, int fraction);
	
	void range(String fieldName, int min, int max);
	
	void allowEmpty(String fieldName);
	
	void notNull(String fieldName);
	
	void notEmpty(String fieldName);
	
	void notBlank(String fieldName);

	void min(String fieldName, int min);
	
	void max(String fieldName, int max);
	
	void length(String fieldName, int min, int max);

	void pattern(String fieldName, String regex, int flag);
	
	void email(String fieldName);
	
	String validate(String fieldName, Object fieldValue) throws ValidateException;

}
