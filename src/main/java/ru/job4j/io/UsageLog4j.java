package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 23;
        char q = 'q';
        byte b = 120;
        short s = 100;
        long l = 10;
        boolean bool = true;
        double d = 1.4;
        float f = 1;
        LOG.debug("int : {}, char : {}, byte : {}, short : {}, long : {}, boolean : {}, double : {}, float : {}", age, q, b, s, l, bool, d, f);
    }
}