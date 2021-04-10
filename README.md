Scenario: Bulk Registration
---------------------------

Problem Statement:

Read CSV with following fields:
MSISDN, SIM_TYPE, NAME, DATE_OF_BIRTH, GENDER, ADDRESS, ID_NUMBER

Function to register SIM:

	- Validation:
		- No field should be empty
		- Name shouldn't have any special character
		- DATE_OF_BIRTH shouldn't be TODAY or FUTURE
		- Gender can only be F or M
		- Address must at least be 20 characters long
		- ID_NUMBER should be a mix of characters & numbers
		- SIM_TYPE can only be PREPAID or POSTPAID
		- MSISDN should comply to country's standard (e.g. +66)
		- MSISDN can't be duplicated

	- For each validated record, save a new file with name as '<MSISDN>.txt' and put all the info there

	- Send welcome SMS to new MSISDN numbers
		- Different message for both genders
		- SMS function can just be log line

	- Console output with status of each registration
		- for rejected registration, print the reason

Pre-Requisite:
Java 11
Maven 3.8.1

Steps to Run Application:
1. Git clone: git clone https://github.com/softready192/bulk-registration.git
2. Navigate to the checkout directory and run : mvn clean install
3. A jar will be created in target folder. Run the jar file: java -jar bulk-registration-0.0.1-RELEASE.jar
4. Open swagger link in browser: http://localhost:8080/swagger-ui.html
5. Use the file-controller rest api to upload the registration.csv, or you can use the sample file located in test/resources directory.