package com.github.coryrobertson.PainAnointer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A class that handles logging to a file, you can log messages to a file here, and give a log level such as a log, an error, or a warning
 */
public class Logger
{
    private static LogLevels level = LogLevels.LOG;

    public Logger()
    {
    }

    /**
     * logs a message to a file, as well as prints it to the console
     * @param s message to log
     * @param l Log level
     * @return true upon successful log of a message to a file
     */
    public static boolean log(String s, LogLevels l)
    {
        
        s = l.getText() + s;
        if(l.value() >= level.value())
        {
            System.out.println(s);
        }

        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;

        try {
            fw = new FileWriter("log.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * logs a message to a file, as well as prints it to the console, also assumes LogLevels.LOG
     * @param s message to log
     */
    public static void log(String s)
    {
        log(s, LogLevels.LOG);
    }

    /**
     * Sets the log level for the entire program
     * @param l the numerical value corresponding to the enum value
     */
    public static void setLevel(LogLevels l)
    {
        level = l;
    }

    /**
     * Gets the current log level as an enum
     * @return the current log level
     */
    public static LogLevels getLevel()
    {
        return level;
    }

    /**
     * Returns a log level enum type depending on the value of n
     *     ERROR(3)
     *     WARN(2)
     *     LOG(1)
     *     NONE(4)
     * @param n a selector to pick a log level
     * @return a log level corresponding to the value of n
     */
    public static LogLevels getLogLevelEnum(int n)
    {
        LogLevels[] enums = LogLevels.class.getEnumConstants();
        for (LogLevels anEnum : enums) {
            if (anEnum.value() == n) {
                return anEnum;
            }
        }
        return null;
    }
}
