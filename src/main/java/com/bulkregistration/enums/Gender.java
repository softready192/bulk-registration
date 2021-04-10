package com.bulkregistration.enums;

import java.util.Arrays;

public enum Gender {

	F, M, UNKNOWN;

	public static String getGender(String input) {

		return Arrays.stream(values()).filter(gender -> gender.name().equalsIgnoreCase(input)).findFirst()
				.orElse(UNKNOWN).name();
	}

}
