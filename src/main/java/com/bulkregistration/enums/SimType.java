package com.bulkregistration.enums;

import java.util.Arrays;

public enum SimType {

	PREPAID, POSTPAID,UNKNOWN;
	
	public static String getSimType(String input) {

		return Arrays.stream(values()).filter(simtype -> simtype.name().equalsIgnoreCase(input)).findFirst()
				.orElse(UNKNOWN).name();
	}
}
