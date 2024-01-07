package ru.job4j.inout.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte varByte = 0;
        short varShort = 0;
        int varInt = 0;
        long varLong = 0L;
        float varFloat = 0.0f;
        double varDouble = 0.0d;
        boolean varBoolean = false;
        char varChar = '\u0000';
        LOG.debug("varByte : {}, varShort : {}, varInt : {}, varLong : {}, varFloat : {}, varDouble : {}, varBoolean : {}, varChar : {} ",
                varByte, varShort, varInt, varLong, varFloat, varDouble, varBoolean, varChar);
    }
}