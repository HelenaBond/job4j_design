package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Petr Arsentev";
        int age = 33;
        LOG.trace("User info name : {}, age : {}", name, age);
        boolean isMan = true;
        float height = 1.5F;
        LOG.info("User is a man : {}, height much greater than : {}", isMan, height);
        byte legs = 2;
        short familiar = Short.MAX_VALUE;
        LOG.warn("User have {} legs, and familiar greater than {} people", legs, familiar);
        long symbols = 100_000_000_000L;
        double miles = 100.0;
        LOG.debug("User wrote more than {} symbols during the development of the course", symbols);
        LOG.debug("User walked more than {} miles through the forest during the development of the course", miles);
        char randomSound = 'e';
        LOG.error("User say '{}' sound", randomSound);

    }
}
