package com.bulkregistration.validator;

import com.bulkregistration.Constants.ErrorConstants;
import com.bulkregistration.enums.Gender;
import com.bulkregistration.enums.SimType;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import static com.bulkregistration.enums.FileHeaders.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class FileValidator {

	private FileValidator() {

	}

	public static String parseMsisdn(List<String> errors, CSVRecord record) {
		String msisdn = record.get(MSISDN.name());
		if (isBlank(msisdn)) {
			errors.add(ErrorConstants.MSISDN_SHOULD_NOT_BE_EMPTY);
		} else if (!Pattern.matches("(\\+\\d{1,3})", msisdn)) {
			errors.add(ErrorConstants.MSISDN_SHOULD_COMPLY_TO_COUNTRY_STANDARD);
		}
		return msisdn;
	}

	public static String parseName(List<String> errors, CSVRecord record) {
		String name = record.get(NAME.name());
		if (isBlank(name)) {
			errors.add(ErrorConstants.NAME_SHOULD_NOT_BE_EMPTY);
		} else if (!Pattern.matches("^[A-Za-z0-9]+$", name)) {
			errors.add(ErrorConstants.NAME_SHOULDNT_SPECIAL_CHARACTER);
		}
		return name;
	}

	public static String parseAddress(List<String> errors, CSVRecord record) {
		String address = record.get(ADDRESS.name());
		if (isBlank(address)) {
			errors.add(ErrorConstants.ADDRESS_SHOULD_NOT_BE_EMPTY);
		} else if (address.length() < 20) {
			errors.add(ErrorConstants.ADDRESS_MUST_BE_20_CHAR_LONG);
		}
		return address;
	}

	public static String parseIdNumber(List<String> errors, CSVRecord record) {
		String idNumber = record.get(ID_NUMBER.name());
		if (isBlank(idNumber)) {
			errors.add(ErrorConstants.ID_NUMBER_SHOULD_NOT_BE_EMPTY);
		} else if (!Pattern.matches("^[a-zA-Z0-9_.-]*$", idNumber)) {
			errors.add(ErrorConstants.ID_NUMBER_SHOULD_BE_MIX_OF_CHAR_AND_NUM);
		}
		return idNumber;
	}

	public static String parseGender(List<String> errors, CSVRecord record) {
		String gender = record.get(GENDER.name());
		if (isBlank(gender)) {
			errors.add(ErrorConstants.GENDER_SHOULD_NOT_BE_EMPTY);
		}
		String validatedGender = Gender.getGender(gender);

		if (Gender.UNKNOWN.name().equalsIgnoreCase(validatedGender)) {
			errors.add(ErrorConstants.GENDER_CAN_BE_F_OR_M);
		}
		return gender;
	}

	public static String parseSimType(List<String> errors, CSVRecord record) {
		String simType = record.get(SIM_TYPE.name());
		if (isBlank(simType)) {
			errors.add(ErrorConstants.SIM_TYPE_SHOULD_NOT_BE_EMPTY);
		}
		String validatedSimType = SimType.getSimType(simType);

		if (SimType.UNKNOWN.name().equalsIgnoreCase(validatedSimType)) {
			errors.add(ErrorConstants.SIM_TYPE_CAN_ONLY_PREPAID_OR_POSTPAID);
		}
		return simType;
	}

	public static String parseDateOfBirth(List<String> errors, CSVRecord record) {
		String dateOfBirth = record.get(DATE_OF_BIRTH.name());
		if (isBlank(dateOfBirth))
			errors.add(ErrorConstants.DOB_SHOULD_NOT_BE_EMPTY);

		try {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate parseDate = LocalDate.parse(dateOfBirth, pattern);
			LocalDate today = LocalDate.now();
			if (parseDate.isAfter(today) || parseDate.isEqual(today)) {
				errors.add(ErrorConstants.DATE_OF_BIRTH_SHOULDNT_TODAY_OR_FUTURE);
			}

		} catch (Exception exception) {
			errors.add("Date of birth should be in yyyy-mm-dd format");
		}
		return dateOfBirth;
	}
}
