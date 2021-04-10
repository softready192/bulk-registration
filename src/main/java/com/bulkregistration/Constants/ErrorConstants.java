package com.bulkregistration.Constants;

public interface ErrorConstants {
	
     String NAME_SHOULD_NOT_BE_EMPTY="Name should not be empty." ;
     String MSISDN_SHOULD_NOT_BE_EMPTY="MSISDN should not be empty.";
     String MSISDN_SHOULD_COMPLY_TO_COUNTRY_STANDARD="MSISDN should comply to country's standard (e.g. +66)";
     String SIM_TYPE_SHOULD_NOT_BE_EMPTY="Sim type should not be empty.";
     String DOB_SHOULD_NOT_BE_EMPTY=" Date of Birth should not be empty.";
     String ID_NUMBER_SHOULD_NOT_BE_EMPTY="Id Number should not be empty.";
     String ADDRESS_SHOULD_NOT_BE_EMPTY="Address should not be empty.";
     String GENDER_SHOULD_NOT_BE_EMPTY="Gender should not be empty.";
     String GENDER_CAN_BE_F_OR_M="Gender can only be F or M";
     String DATE_OF_BIRTH_SHOULDNT_TODAY_OR_FUTURE= "DATE_OF_BIRTH shouldn't be TODAY or FUTURE";
     String NAME_SHOULDNT_SPECIAL_CHARACTER="Name shouldn't have any special character";
     String ADDRESS_MUST_BE_20_CHAR_LONG="Address must at least be 20 characters long";
     String ID_NUMBER_SHOULD_BE_MIX_OF_CHAR_AND_NUM="ID_NUMBER should be a mix of characters & numbers";
     String SIM_TYPE_CAN_ONLY_PREPAID_OR_POSTPAID="SIM_TYPE can only be PREPAID or POSTPAID";
     String MSISDN_CANT_BE_DUPLICATE ="MSISDN can't be duplicated";
}
