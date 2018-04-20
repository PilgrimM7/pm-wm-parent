/**
 * 
 */
package com.pilgrimm.core.validate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liwei
 */
public final class DefaultValidate implements Validate {

	private Map<String, ValidateTest> validFields = new HashMap<String, ValidateTest>();

	private interface ValidateTest {
		String test(Object filedValue) throws ValidateException;
	}

	/**
	 * 
	 */
	public DefaultValidate() {
	}

	private boolean notMatches(String value, String regex) {
		return !value.matches(regex);
	}

	public void date(String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
				if (notMatches(filedValue.toString(), regex)) {
					return null;
				}

				return filedValue.toString();
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void datetime(String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				String regex = "^(((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8])))))) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d)";
				if (notMatches(filedValue.toString(), regex)) {
					return null;
				}

				return filedValue.toString();
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void integer(final String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				if ("".equals(filedValue.toString().trim())) {
					return null;
				}

				String regex = "^$|^-?((0|[1-9]\\d*))$";
				if (notMatches(filedValue.toString(), regex)) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue
							+ "] out of range, expect a integer.");
				}

				int num = Integer.parseInt(filedValue.toString());

				return String.valueOf(num);
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void decimal(final String fieldName, final int fraction) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				if ("".equals(filedValue.toString().trim())) {
					return null;
				}

				String regex = "^$|^-?(\\d*\\.\\d{" + fraction + "})$";
				if (notMatches(filedValue.toString(), regex)) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue
							+ "] out of range, expect fraction " + fraction
							+ " figures.");
				}

				DecimalFormat formater = new DecimalFormat();
				formater.setMinimumFractionDigits(fraction);

				double num = Double.parseDouble(filedValue.toString());

				return formater.format(num);
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void digits(final String fieldName, final int integer,
			final int fraction) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				if ("".equals(filedValue.toString().trim())) {
					return null;
				}

				String regex = "^$|^-?((\\d{1," + integer + "})\\.\\d{"
						+ fraction + "})$";
				if (notMatches(filedValue.toString(), regex)) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue
							+ "] out of range, expect max integer " + integer
							+ " figures, fraction " + fraction + " figures.");
				}

				DecimalFormat formater = new DecimalFormat();
				formater.setMinimumFractionDigits(fraction);

				double num = Double.parseDouble(filedValue.toString());

				return formater.format(num);
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void range(final String fieldName, final int min, final int max) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				int num = Integer.parseInt(filedValue.toString());
				if (num < min || num > max) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue
							+ "] out of range, expect inter between " + min
							+ " to " + max + ".");
				}

				DecimalFormat formater = new DecimalFormat();

				return formater.format(num);
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void allowEmpty(String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					return null;
				}

				if ("".equals(filedValue.toString().trim())) {
					return null;
				}

				return filedValue.toString();
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void notNull(final String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue + "] is null.");
				}

				return filedValue.toString();
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void notEmpty(final String fieldName) {
		ValidateTest validTest = new ValidateTest() {
			public String test(Object filedValue) throws ValidateException {
				if (filedValue == null) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue + "] is null.");
				}

				if ("".equals(filedValue.toString().trim())) {
					throw new ValidateException("Field [" + fieldName
							+ "] 's value [" + filedValue + "] is empty.");
				}

				return filedValue.toString();
			}
		};

		validFields.put(fieldName, validTest);
	}

	public void notBlank(final String fieldName) {

	}

	public void min(final String fieldName, final int min) {

	}

	public void max(final String fieldName, final int max) {

	}

	public void length(final String fieldName, final int min, final int max) {

	}

	public void pattern(final String fieldName, final String regex,
			final int flag) {

	}

	public void email(final String fieldName) {

	}

	public String validate(String fieldName, Object filedValue)
			throws ValidateException {
		return validFields.get(fieldName) == null ? filedValue.toString()
				: validFields.get(fieldName).test(filedValue);
	}

	public static void main(String[] args) throws ValidateException {
		// String date = "2008-2-28";
		// String datetime = "2008-2-28 00:01:01";
		// String range = "01";
		// String integer = "3";
		// String decimal = "0.01";
		// String digits = "021.10";
		// String allowEmpty = "$223424ad112";
		//
		// DefaultValidate valid = new DefaultValidate();
		// valid.date("date");
		// valid.datetime("datetime");
		// valid.range("range", -80, 80);
		// valid.integer("integer");
		// valid.decimal("decimal", 2);
		// valid.digits("digits", 3, 2);
		// valid.allowEmpty("allowEmpty");
		//
		// System.out.println(valid.validate("date", date));
		// System.out.println(valid.validate("datetime", datetime));
		// System.out.println(valid.validate("range", range));
		// System.out.println(valid.validate("integer", integer));
		// System.out.println(valid.validate("decimal", decimal));
		// System.out.println(valid.validate("digits", digits));
		// System.out.println(valid.validate("allowEmpty", allowEmpty));
	}

}
