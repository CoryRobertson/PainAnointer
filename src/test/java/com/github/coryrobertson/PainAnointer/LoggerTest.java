package com.github.coryrobertson.PainAnointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerTest
{

    @Test
    void log()
    {
        Logger.setLevel(LogLevels.LOG);
        Assertions.assertTrue(Logger.log("1 This is a test of the log, pay no attention here :)", LogLevels.LOG));
        Assertions.assertTrue(Logger.log("1 This is a test of the log, pay no attention here :)", LogLevels.WARN));
        Assertions.assertTrue(Logger.log("1 This is a test of the log, pay no attention here :)", LogLevels.ERROR));
        Logger.setLevel(LogLevels.WARN);
        Assertions.assertTrue(Logger.log("2 This is a test of the log, pay no attention here :)", LogLevels.LOG));
        Assertions.assertTrue(Logger.log("2 This is a test of the log, pay no attention here :)", LogLevels.WARN));
        Assertions.assertTrue(Logger.log("2 This is a test of the log, pay no attention here :)", LogLevels.ERROR));
        Logger.setLevel(LogLevels.ERROR);
        Assertions.assertTrue(Logger.log("3 This is a test of the log, pay no attention here :)", LogLevels.LOG));
        Assertions.assertTrue(Logger.log("3 This is a test of the log, pay no attention here :)", LogLevels.WARN));
        Assertions.assertTrue(Logger.log("3 This is a test of the log, pay no attention here :)", LogLevels.ERROR));
    }

    @Test
    void getLogLevelEnum()
    {
        Assertions.assertEquals(LogLevels.LOG, Logger.getLogLevelEnum(1));
        Assertions.assertEquals(LogLevels.WARN, Logger.getLogLevelEnum(2));
        Assertions.assertEquals(LogLevels.ERROR, Logger.getLogLevelEnum(3));
    }
}