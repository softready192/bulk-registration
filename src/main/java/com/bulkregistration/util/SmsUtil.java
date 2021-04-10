package com.bulkregistration.util;

import com.bulkregistration.enums.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SmsUtil.class);

    private SmsUtil() {

    }

    public static void sendSMS(String gender) {
        if (Gender.F.name().equalsIgnoreCase(gender)) {
            LOG.info("Dear Mam, Welcome! Your registration is successful.");
        } else if (Gender.M.name().equalsIgnoreCase(gender)) {
            LOG.info("Dear Sir, Welcome! Your registration is successful.");
        }
    }

}
