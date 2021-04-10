package com.bulkregistration.processor;

import com.bulkregistration.Constants.ErrorConstants;
import com.bulkregistration.exception.FileProcessingException;
import com.bulkregistration.model.Customer;
import com.bulkregistration.util.FileUtil;
import com.bulkregistration.util.SmsUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bulkregistration.validator.FileValidator.*;

@Service
public class FileProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(FileProcessor.class);
    public static final char DELIMITER = ',';

    public void process(InputStream inputStream) throws FileProcessingException {
        Set<Customer> customers = new HashSet<>();
        List<CSVRecord> csvRecords = parseInputFile(inputStream);
        csvRecords.forEach(record -> this.processRecords(customers, record));
    }

    private void processRecords(Set<Customer> customers, CSVRecord record) {
        List<String> errors = new ArrayList<>();
        Customer customer = getCustomer(record, errors);
        if (errors.isEmpty() && !customers.add(customer)) {
            errors.add(ErrorConstants.MSISDN_CANT_BE_DUPLICATE);
        }
        if (!errors.isEmpty()) {
            LOG.error("Registration failed for Msisdn NO : {}  due to following errors: {} ", customer.getMsisdn(), errors);
        } else {
            LOG.info("Registration successfully with Msisdn NO : {}", customer.getMsisdn());
            FileUtil.writeFile(customer.getMsisdn(), customer.toString());
            SmsUtil.sendSMS(customer.getGender());
        }
    }

    private Customer getCustomer(CSVRecord record, List<String> errors) {
        var customer = new Customer();
        customer.setMsisdn(parseMsisdn(errors, record));
        customer.setSimType(parseSimType(errors, record));
        customer.setName(parseName(errors, record));
        customer.setDob(parseDateOfBirth(errors, record));
        customer.setGender(parseGender(errors, record));
        customer.setAddress(parseAddress(errors, record));
        customer.setIdNumber(parseIdNumber(errors, record));
        return customer;
    }

    private List<CSVRecord> parseInputFile(InputStream inputStream) {
        try {
            CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader().withDelimiter(DELIMITER);
            CSVParser csvFileParser = new CSVParser(new InputStreamReader(inputStream), csvFileFormat);
            return csvFileParser.getRecords();
        } catch (IOException e) {
            throw new FileProcessingException("File processing failed", e);
        }
    }
}
